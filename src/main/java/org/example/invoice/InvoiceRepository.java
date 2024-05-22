package org.example.invoice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.entities.Invoice;
import org.example.entities.InvoiceItem;
import org.example.entities.Item;
import org.example.entities.Stock;
import org.example.exceptions.ValidationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public class InvoiceRepository {
    @PersistenceContext
    private EntityManager entityManager;

@Transactional
public Invoice importInvoice(Invoice invoice) {
    Invoice savedInvoice = saveInvoice(invoice);
    processInvoiceItems(savedInvoice);
    return savedInvoice;
}

private Invoice saveInvoice(Invoice invoice) {
    return entityManager.merge(invoice);
}

private void processInvoiceItems(Invoice savedInvoice) {
    Set<InvoiceItem> invoiceItems = savedInvoice.getInvoiceItem();
    invoiceItems.forEach(invoiceItem -> processInvoiceItem(invoiceItem, savedInvoice));
    savedInvoice.setInvoiceItem(invoiceItems);
}

private void processInvoiceItem(InvoiceItem invoiceItem, Invoice savedInvoice) {
    saveInvoiceItemAndSetInvoiceItemId(invoiceItem, savedInvoice);
    saveStock(invoiceItem, savedInvoice);
    persistItemAfterStockChange(invoiceItem);
}

private void persistItemAfterStockChange(InvoiceItem invoiceItem) {
    Item item = changeStockQty(invoiceItem);
    entityManager.persist(item);
}

    private void saveInvoiceItemAndSetInvoiceItemId(InvoiceItem invoiceItem, Invoice savedInvoice) {
        invoiceItem.setInvoiceId(savedInvoice.getInvoiceId());
        InvoiceItem savedInvoiceItem = entityManager.merge(invoiceItem);
        invoiceItem.setInvoiceItemId(savedInvoiceItem.getInvoiceItemId());
    }

    private void saveStock(InvoiceItem invoiceItem, Invoice invoice) {
        Stock stock = getStockByItemId(invoiceItem.getItemId());
        if (invoice.getInvoiceType() == InvoiceService.PURCHASE_INVOICE) stock.setQty(stock.getQty() + invoiceItem.getQtySold());
        else {
            stock.setQty(stock.getQty() - invoiceItem.getQtySold());
            if (stock.getQty() < 0)
                throw new ValidationException("There's not enough product id: " + invoiceItem.getItemId() + " on stock");
        }
        entityManager.persist(stock);
    }

    private Item changeStockQty(InvoiceItem invoiceItem) {
        Item item = entityManager.find(Item.class, invoiceItem.getItemId());
        item.setPurchaseNetPrice(invoiceItem.getNetValue());
        item.setPurchaseGrossPrice(invoiceItem.getGrossValue());
        return item;
    }

    private Stock getStockByItemId(Long itemId) {
        return (Stock) entityManager.createNativeQuery("SELECT * FROM stock WHERE item_id = :itemId", Stock.class)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }

    public Invoice getInvoiceById(Long invoiceId) {
        return entityManager.find(Invoice.class, invoiceId);
    }

    public void updateInvoice(Invoice invoiceToSend) {
        entityManager.merge(invoiceToSend);
    }

    public List<Invoice> getInvoices(Integer isPaid, Integer invoiceType) {
        return (List<Invoice>) entityManager.createNativeQuery("SELECT * FROM invoice WHERE is_paid = :isPaid AND invoice_type = :invoiceType", Invoice.class)
                .setParameter("isPaid", isPaid)
                .setParameter("invoiceType", invoiceType)
                .getResultList();
    }
}
