package org.example.invoice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.entieties.Invoice;
import org.example.entieties.InvoiceItem;
import org.example.entieties.Item;
import org.example.entieties.Stock;
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
        for (InvoiceItem i : invoiceItems) {
            i.setInvoiceId(savedInvoice.getInvoiceId());
            InvoiceItem savedInvoiceItem = entityManager.merge(i);
            i.setInvoiceItemId(savedInvoiceItem.getInvoiceItemId());
            Stock stock = getStockByItemId(i.getItemId());
            stock.setQty(stock.getQty() + i.getQtySold());
            entityManager.persist(stock);
            Item item = entityManager.find(Item.class, i.getItemId());
            item.setPurchaseNetPrice(i.getNetValue());
            item.setPurchaseGrossPrice(i.getGrossValue());
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

}
