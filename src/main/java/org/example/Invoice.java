package org.example;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "Invoice_json")
    String invoiceJSON;
    @Column(name = "Ksef_status")
    int ksefStatus;
    @Column(name = "Sent_status")
    int sentStatus;
    @JoinColumn(name = "Customer_id")
    @ManyToOne
    Customers customer;
    @OneToMany(mappedBy = "invoice")
    Set<OrderedItem> orderedItems;


    public Invoice(long id, String invoiceJSON, int ksefStatus, int sentStatus, Customers customer, Set<OrderedItem> orderedItems) {
        this.id = id;
        this.invoiceJSON = invoiceJSON;
        this.ksefStatus = ksefStatus;
        this.sentStatus = sentStatus;
        this.customer = customer;
        this.orderedItems = orderedItems;
    }

    public Set<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Set<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInvoiceJSON() {
        return invoiceJSON;
    }

    public void setInvoiceJSON(String invoiceJSON) {
        this.invoiceJSON = invoiceJSON;
    }

    public int getKsefStatus() {
        return ksefStatus;
    }

    public void setKsefStatus(int ksefStatus) {
        this.ksefStatus = ksefStatus;
    }

    public int getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(int sentStatus) {
        this.sentStatus = sentStatus;
    }
}
