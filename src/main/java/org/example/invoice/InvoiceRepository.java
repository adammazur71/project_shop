package org.example.invoice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.entieties.Invoice;
import org.example.entieties.InvoiceItem;
import org.example.entieties.Item;
import org.example.entieties.Stock;
import org.example.exceptions.ValidationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
            invoiceItem.setInvoiceId(savedInvoice.getInvoiceId());
            InvoiceItem savedInvoiceItem = entityManager.merge(invoiceItem);
            invoiceItem.setInvoiceItemId(savedInvoiceItem.getInvoiceItemId());
            Stock stock = getStockByItemId(invoiceItem.getItemId());
            stock.setQty(stock.getQty() + invoiceItem.getQtySold());
            entityManager.persist(stock);
            Item item = entityManager.find(Item.class, invoiceItem.getItemId());
            item.setPurchaseNetPrice(invoiceItem.getNetValue());
            item.setPurchaseGrossPrice(invoiceItem.getGrossValue());
            entityManager.persist(item);
        }
        savedInvoice.setInvoiceItem(invoiceItems);
        return savedInvoice;
    }

    private Stock getStockByItemId(Long itemId) {
        return (Stock) entityManager.createNativeQuery("SELECT * FROM stock WHERE item_id = :itemId", Stock.class)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }

    @Transactional
    public Invoice exportInvoice(Invoice invoice) {
        Invoice savedInvoice = entityManager.merge(invoice);
        Set<InvoiceItem> invoiceItems = invoice.getInvoiceItem();
        for (InvoiceItem invoiceItem : invoiceItems) {
            invoiceItem.setInvoiceId(savedInvoice.getInvoiceId());
            InvoiceItem savedInvoiceItem = entityManager.merge(invoiceItem);
            invoiceItem.setInvoiceItemId(savedInvoiceItem.getInvoiceItemId());
            Stock stock = getStockByItemId(invoiceItem.getItemId());
            stock.setQty(stock.getQty() - invoiceItem.getQtySold());
            if (stock.getQty() < 0)
                throw new ValidationException("There's not enough product id: " + invoiceItem.getItemId() + " on stock");
            entityManager.persist(stock);
            Item item = entityManager.find(Item.class, invoiceItem.getItemId());
            item.setSellingNetPrice(invoiceItem.getNetValue());
            item.setSellingGrossPrice(invoiceItem.getGrossValue());
            entityManager.persist(item);
        }
        savedInvoice.setInvoiceItem(invoiceItems);
        return savedInvoice;
    }
}
