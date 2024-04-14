package org.example;

import javax.persistence.*;

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

    public Invoice(long id, String invoiceJSON, int ksefStatus, int sentStatus) {
        this.id = id;
        this.invoiceJSON = invoiceJSON;
        this.ksefStatus = ksefStatus;
        this.sentStatus = sentStatus;
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
