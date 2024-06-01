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
    @Column(name = "item_name")
    String itemName;
    @Column(name = "purchase_net_price")
    Double purchaseNetPrice;
    @Column(name = "vat_stake")
    Double vatStake;
    @Column(name = "purchase_gross_price")
    Double purchaseGrossPrice;
    @Column(name = "Selling_net_price")
    Double sellingNetPrice;
    @Column(name = "Selling_gross_price")
    Double sellingGrossPrice;
    @JoinColumn(name = "Supplier_id")
    @ManyToOne
    Customer customer;
    @OneToMany
    @JoinColumn(name = "item_id")
    Set<InvoiceItem> invoiceItems;
    @OneToOne
    @JoinColumn(name ="item_id")
    Stock stock;


}
