package org.example;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Shipments")
public class Shipments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long shipmentId;
    @Column(name = "Shipment_date")
    Date shipmentDate;
    @Column(name = "Employee_id")
    long employeeId;
    @Column(name = "Status")
    int status;
    @Column(name = "Shipment_method")
    long shipmentMethod;

    public Shipments(long shipmentId, Date shipmentDate, long employeeId, int status, long shipmentMethod) {
        this.shipmentId = shipmentId;
        this.shipmentDate = shipmentDate;
        this.employeeId = employeeId;
        this.status = status;
        this.shipmentMethod = shipmentMethod;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(long shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }
}
