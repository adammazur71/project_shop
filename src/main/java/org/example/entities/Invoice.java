package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "invoice")
// TODO: JAVA DOC
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long invoiceId;
    @Column(name = "invoice_type", columnDefinition = "INT(1) NOT NULL CHECK(invoice_type BETWEEN 0  AND 1) COMMENT '0 - buying, 1 - selling'")
    Integer invoiceType;
    @Column(name = "invoice_no")
    String invoiceNo;
    @Column(name= "is_paid", columnDefinition = "INT(1) NOT NULL CHECK(is_paid BETWEEN 0  AND 1) COMMENT '0 - unpaid, 1 - paid'")
    Integer isPaid;
    @OneToMany(mappedBy = "invoice")
    Set<Shipments> shipments;
    @Column(name = "ksef_id")
    String ksefId;
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
