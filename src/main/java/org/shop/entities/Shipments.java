package org.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Shipments")
public class Shipments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentId;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "shipment_method_id")
    private ShipmentMethod shipmentMethod;
    @Column(name = "Shipment_date")
    private Date shipmentDate;
    @JoinColumn(name = "Employee_id")
    @ManyToOne
    private Employees employee;
    @Column(name = "Status")
    private Integer status;

}
