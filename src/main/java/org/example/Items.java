package org.example;

import javax.persistence.*;

@Entity
@Table(name = "Items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long item_id;
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
    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getPurchaseVat() {
        return purchaseVat;
    }

    public void setPurchaseVat(double purchaseVat) {
        this.purchaseVat = purchaseVat;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getSellingVat() {
        return sellingVat;
    }

    public void setSellingVat(double sellingVat) {
        this.sellingVat = sellingVat;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Items(long item_id, String name, double purchasePrice, double purchaseVat, double sellingPrice, double sellingVat, int supplierId, int amount) {
        this.item_id = item_id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.purchaseVat = purchaseVat;
        this.sellingPrice = sellingPrice;
        this.sellingVat = sellingVat;
        this.supplierId = supplierId;
        this.amount = amount;
    }
}
