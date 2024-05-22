package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Shipment_method")
// TODO: JAVA DOC
public class ShipmentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long shipmentMethodId;
    @Column(name = "name")
    String name;

}
