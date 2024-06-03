package org.shop.entities;

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
    private Long invoiceItemId;
    @Column(name = "item_id")
    private Long itemId;
    @Column(name = "invoice_id")
    private Long invoiceId;
    @Column(name = "qty_sold")
    private Integer qtySold;
    @Column(name = "net_value")
    private Double netValue;
    @Column(name = "vat_stake")
    private Double vatStake;
    @Column(name = "gross_value")
    private Double grossValue;



}
