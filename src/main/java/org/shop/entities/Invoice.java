package org.shop.entities;

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
    private Long invoiceId;
    @Column(name = "invoice_type", columnDefinition = "INT(1) NOT NULL CHECK(invoice_type BETWEEN 0  AND 1) COMMENT '0 - buying, 1 - selling'")
    private Integer invoiceType;
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Column(name= "is_paid", columnDefinition = "INT(1) NOT NULL CHECK(is_paid BETWEEN 0  AND 1) COMMENT '0 - unpaid, 1 - paid'")
    private Integer isPaid;
    @OneToMany(mappedBy = "invoice")
    private Set<Shipments> shipments;
    @Column(name = "ksef_id")
    private String ksefId;
    @Column(name = "net_amount")
    private Double netAmount;
    @Column(name = "gross_amount")
    private Double grossAmount;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany
    @JoinColumn(name = "invoice_id")
    private Set<InvoiceItem> invoiceItems;
}
