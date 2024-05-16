package org.example.entieties;

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
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long invoiceId;
    @Column(name = "invoice_type", columnDefinition = "INT(1) NOT NULL CHECK(invoice_type BETWEEN 0  AND 1) COMMENT '0 - zakupowa, 1 - sprzedaży'")
    Integer invoiceType;
    @OneToMany(mappedBy = "invoice")
    Set<Shipments> shipments;
    @Column(name = "ksef_status")
    Integer ksefStatus;
    @Column(name = "net_amount")
    Double netAmount;
    @Column(name = "gross_amount")
    Double grossAmount;
    @JoinColumn(name = "customer_id")
    @ManyToOne
    Customer customer;
    @OneToMany
    @JoinColumn(name = "invoice_id")
    Set<InvoiceItem> invoiceItem;

}
