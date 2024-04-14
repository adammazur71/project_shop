package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Ordered_item")
public class OrderedItem {
    @Column(name = "Order_id")
    long orderId;
    @Column(name = "Item_id")
    long itemId;
    @Column(name = "Sold_price")
    double soldPrice;
    @Column(name = "Sold_vat")
    double soldVat;
    @Column(name = "qty")
    int qty;

    public OrderedItem(long orderId, long itemId, double soldPrice, double soldVat, int qty) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.soldPrice = soldPrice;
        this.soldVat = soldVat;
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
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
