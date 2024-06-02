package org.shop.entieties;

import lombok.*;

import jakarta.persistence.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @JoinColumn(name = "item_type_id")
    @ManyToOne
    private ItemType itemType;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "purchase_net_price")
    private Double purchaseNetPrice;
    @Column(name = "vat_stake")
    private Double vatStake;
    @Column(name = "purchase_gross_price")
    private Double purchaseGrossPrice;
    @Column(name = "Selling_net_price")
    private Double sellingNetPrice;
    @Column(name = "Selling_gross_price")
    private Double sellingGrossPrice;
    @JoinColumn(name = "Supplier_id")
    @ManyToOne
    private Customer customer;
    @OneToMany
    @JoinColumn(name = "item_id")
    private Set<InvoiceItem> invoiceItems;
    @OneToOne(mappedBy = "item")
    private Stock stock;


}
