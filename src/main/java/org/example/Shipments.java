package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Shipments")
public class Shipments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long shipmentId;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    Invoice invoice;
    @Column(name = "Shipment_date")
    Date shipmentDate;
    @Column(name = "Employee_id")
    long employeeId;
    @Column(name = "Status")
    int status;
    @Column(name = "Shipment_method")
    long shipmentMethod;


}
