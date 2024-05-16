package org.example.entieties;

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
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long invoiceItemId;
    @Column(name = "item_id")
    Long itemId;
    @Column(name = "invoice_id")
    Long invoiceId;
    @Column(name = "qty_sold")
    Integer qtySold;
    @Column(name = "net_value")
    Double netValue;
    @Column(name = "gross_value")
    Double grossValue;



}
