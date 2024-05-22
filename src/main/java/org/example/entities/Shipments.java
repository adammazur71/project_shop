package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Shipments")
// TODO: JAVA DOC
public class Shipments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long shipmentId;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "shipment_method_id")
    ShipmentMethod shipmentMethod;
    @Column(name = "Shipment_date")
    Date shipmentDate;
    @JoinColumn(name = "Employee_id")
    @ManyToOne
    Employees employee;
    @Column(name = "Status")
    Integer status;

}
