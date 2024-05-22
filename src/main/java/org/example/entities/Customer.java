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
@Table(name = "Customers")
// TODO: JAVA DOC
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_id")
    Long customerId;
    @Column(name = "customer_name")
    String customerName;
    @Column(name = "Nip")
    String nip;
    @Column(name = "Street")
    String street;
    @Column(name = "Building_no")
    String buildingNo;
    @Column(name = "Apartment_no")
    String apartmentNo;
    @Column(name = "Postal")
    Integer postal;
    @Column(name = "City")
    String city;
    @Column(name = "Country")
    String country;
    @Column(name = "Phone_no")
    String phoneNo;
    @Column(name = "e-mail")
    String email;
    @Column(name = "Role", columnDefinition = "INT(1) NOT NULL CHECK(role BETWEEN 0  AND 2) COMMENT 'funkcja: 0 - klient, 1 - dostawca, 2 - oba'")
    Integer role;

}
