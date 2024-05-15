package org.example.entieties;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "Shipment_method")
public class ShipmentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long shipmentMethodId;
    @Column(name = "name")
    String name;

    public ShipmentMethod(long shipmentMethodId, String name) {
        this.shipmentMethodId = shipmentMethodId;
        this.name = name;
    }

    public long getShipmentMethodId() {
        return shipmentMethodId;
    }

    public void setShipmentMethodId(long shipmentMethodId) {
        this.shipmentMethodId = shipmentMethodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
