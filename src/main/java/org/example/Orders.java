package org.example;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long orderId;
    @Column(name = "Customer_id")
    long customerId;
    @Column(name = "Date")
    Date date;
    @Column(name = "Payment_status")
    int paymentStatus;
    @JoinColumn(name = "Employee_id")
    @ManyToOne
    Employees employee;
    @Column(name = "Order_value")
    double orderValue;
    @Column(name = "Order_vat")
    double orderVat;
    @OneToOne
    @JoinColumn(name = "Invoice_id")
    Invoice invoice;
    @OneToMany(mappedBy = "orderId")
    Set<OrderedItem> orderedItems;

    public Orders(long orderId, long customerId, Date date, int paymentStatus, Employees employee, double orderValue, double orderVat, Invoice invoice, Set<OrderedItem> orderedItems) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.date = date;
        this.paymentStatus = paymentStatus;
        this.employee = employee;
        this.orderValue = orderValue;
        this.orderVat = orderVat;
        this.invoice = invoice;
        this.orderedItems = orderedItems;
    }

    public Employees getEmployee() {
        return employee;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Set<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Set<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(double orderValue) {
        this.orderValue = orderValue;
    }

    public double getOrderVat() {
        return orderVat;
    }

    public void setOrderVat(double orderVat) {
        this.orderVat = orderVat;
    }
}
