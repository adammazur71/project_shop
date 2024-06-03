package org.shop.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_id")
    private Long customerId;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "Nip")
    private String nip;
    @Column(name = "Street")
    private String street;
    @Column(name = "Building_no")
    private String buildingNo;
    @Column(name = "Apartment_no")
    private String apartmentNo;
    @Column(name = "Postal")
    private Integer postal;
    @Column(name = "City")
    private String city;
    @Column(name = "Country")
    private String country;
    @Column(name = "Phone_no")
    private String phoneNo;
    @Column(name = "e-mail")
    private String email;
    @Column(name = "Role", columnDefinition = "INT(1) NOT NULL CHECK(role BETWEEN 0  AND 2) COMMENT 'funkcja: 0 - klient, 1 - dostawca, 2 - oba'")
    private Integer role;

}
