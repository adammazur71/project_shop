package org.shop.invoice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.shop.entieties.Invoice;
import org.shop.entieties.InvoiceItem;
import org.shop.entieties.Item;
import org.shop.entieties.Stock;
import org.shop.exceptions.ValidationException;
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
        Invoice savedInvoice = entityManager.merge(invoice);
        Set<InvoiceItem> invoiceItems = invoice.getInvoiceItem();
        for (InvoiceItem invoiceItem : invoiceItems) {
            saveInvoiceItemAndSetInvoiceItemId(invoiceItem, savedInvoice);
            saveStock(invoiceItem, invoice);
            Item item = saveNewItemPrice(invoiceItem, invoice.getInvoiceType());
            entityManager.persist(item);
        }
        savedInvoice.setInvoiceItem(invoiceItems);
        return savedInvoice;
    }

    private void saveInvoiceItemAndSetInvoiceItemId(InvoiceItem invoiceItem, Invoice savedInvoice) {
        invoiceItem.setInvoiceId(savedInvoice.getInvoiceId());
        InvoiceItem savedInvoiceItem = entityManager.merge(invoiceItem);
        invoiceItem.setInvoiceItemId(savedInvoiceItem.getInvoiceItemId());
    }

    private void saveStock(InvoiceItem invoiceItem, Invoice invoice) {
        Stock stock = getStockByItemId(invoiceItem.getItemId());
        if (invoice.getInvoiceType() == InvoiceService.PURCHASE_INVOICE)
            stock.setQty(stock.getQty() + invoiceItem.getQtySold());
        else {
            stock.setQty(stock.getQty() - invoiceItem.getQtySold());
            if (stock.getQty() < 0)
                throw new ValidationException("There's not enough product id: " + invoiceItem.getItemId() + " on stock");
        }
        entityManager.persist(stock);
    }

    private Item saveNewItemPrice(InvoiceItem invoiceItem, int invoiceType) {
        Item item = entityManager.find(Item.class, invoiceItem.getItemId());
        if (invoiceType == InvoiceService.PURCHASE_INVOICE) {
            item.setPurchaseNetPrice(invoiceItem.getNetValue());
            item.setPurchaseGrossPrice(invoiceItem.getGrossValue());
        } else {
            item.setSellingNetPrice(invoiceItem.getNetValue());
            item.setSellingGrossPrice(invoiceItem.getGrossValue());
        }
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
