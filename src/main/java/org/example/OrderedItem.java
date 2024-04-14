package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Ordered_item")
public class OrderedItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    long id;
    @JoinColumn(name = "Order_id")
    long orderId;
    @Column(name = "Item_id")
    long itemId;
    @Column(name = "Sold_price")
    double soldPrice;
    @Column(name = "Sold_vat")
    double soldVat;
    @Column(name = "qty")
    int qty;
    @JoinColumn(name = "Invoice_id")
    @ManyToOne
    Invoice invoice;


}
