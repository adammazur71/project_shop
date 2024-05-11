package org.example.Entieties;

import lombok.*;

import jakarta.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long itemId;
    @JoinColumn(name = "item_type_id")
    @ManyToOne
    ItemType itemType;
    @Column(name = "Name")
    String name;
    @Column(name = "Purchase_price")
    double purchasePrice;
    @Column(name = "Purchase_vat")
    double purchaseVat;
    @Column(name = "Selling_price")
    double sellingPrice;
    @Column(name = "Selling_vat")
    double sellingVat;
    @JoinColumn(name = "Supplier_id")
    @ManyToOne
    Customers customer;

}
