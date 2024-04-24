package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Invoice_items")
public class InvoiceItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long invoiceItemId;
    @ManyToOne
    @JoinColumn(name = "item_id")
    Items item;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    Invoice invoice;
    @Column(name = "qty_sold")
    int qtySold;
    @Column(name = "net_value")
    double netValue;
    @Column(name = "gross_value")
    double grossValue;



}
