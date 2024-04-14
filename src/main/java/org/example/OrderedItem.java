package org.example;

import javax.persistence.*;

@Entity
@Table(name = "Ordered_item")
public class OrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @JoinColumn(name = "Order_id")
    Orders order;
    @Column(name = "Item_id")
    Items item;
    @Column(name = "Sold_price")
    double soldPrice;
    @Column(name = "Sold_vat")
    double soldVat;
    @Column(name = "qty")
    int qty;
    @JoinColumn(name = "Invoice_id")
    Invoice invoice;

    public OrderedItem(long id, Orders order, Items item, double soldPrice, double soldVat, int qty, Invoice invoice) {
        this.id = id;
        this.order = order;
        this.item = item;
        this.soldPrice = soldPrice;
        this.soldVat = soldVat;
        this.qty = qty;
        this.invoice = invoice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getQty() {
        return qty;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public double getSoldVat() {
        return soldVat;
    }

    public void setSoldVat(double soldVat) {
        this.soldVat = soldVat;
    }
}
