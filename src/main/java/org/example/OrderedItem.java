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

    public OrderedItem(long orderId, long itemId, double soldPrice, double soldVat) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.soldPrice = soldPrice;
        this.soldVat = soldVat;
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
