package org.example.Entieties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
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
    Item item;
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
