package org.example.Entieties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long invoiceId;
    @Column(name = "Invoice_type", columnDefinition = "INT(1) NOT NULL CHECK(invoice_type BETWEEN 0  AND 1) COMMENT '0 - zakupowa, 1 - sprzedaży'")
    int invoiceType;
    @Column(name = "Invoice_json")
    String invoiceJSON;
    @OneToMany(mappedBy = "invoice")
    Set<Shipments> shipments;
    @Column(name = "Ksef_status")
    int ksefStatus;
    @JoinColumn(name = "Customer_id")
    @ManyToOne
    Customers customer;
    @OneToMany(mappedBy = "invoice")
    Set<InvoiceItems> invoiceItem;

}