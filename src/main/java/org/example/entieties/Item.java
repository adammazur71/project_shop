package org.example.entieties;

import lombok.*;

import jakarta.persistence.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long itemId;
    @JoinColumn(name = "item_type_id")
    @ManyToOne
    ItemType itemType;
    @Column(name = "Item_Name")
    String itemName;
    @Column(name = "Purchase_price")
    Double purchaseNetPrice;
    @Column(name = "Purchase_vat")
    Double purchaseGrossPrice;
    @Column(name = "Selling_price")
    Double sellingNetPrice;
    @Column(name = "Selling_vat")
    Double sellingGrossPrice;
    @JoinColumn(name = "Supplier_id")
    @ManyToOne
    Customer customer;
    @OneToMany
    @JoinColumn(name = "item_id")
    Set<InvoiceItem> invoiceItems;


}
