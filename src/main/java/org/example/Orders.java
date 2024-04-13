package org.example;

import javax.persistence.*;
import java.util.Date;

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
    @Column(name = "Employee_id")
    long employeeId;
    @Column(name = "Order_value")
    double orderValue;
    @Column(name = "Order_vat")
    double orderVat;

    public Orders(long orderId, long customerId, Date date, int paymentStatus, long employeeId, double orderValue, double orderVat) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.date = date;
        this.paymentStatus = paymentStatus;
        this.employeeId = employeeId;
        this.orderValue = orderValue;
        this.orderVat = orderVat;
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

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
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
