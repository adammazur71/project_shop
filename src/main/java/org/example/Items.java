package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long itemId;
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
    @Column(name = "Amount")
    int amount;
    @JoinColumn(name = "Supplier_id")
    @ManyToOne
    Customers customer;

}
