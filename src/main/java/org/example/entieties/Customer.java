package org.example.entieties;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_id")
    long customerId;
    @Column(name = "name")
    String customerName;
    @Column(name = "Street")
    String street;
    @Column(name = "Building_no")
    String buildingNo;
    @Column(name = "Apartment_no")
    String apartmentNo;
    @Column(name = "Postal")
    int postal;
    @Column(name = "City")
    String city;
    @Column(name = "Country")
    String country;
    @Column(name = "Phone_no")
    int phoneNo;
    @Column(name = "Alt_phone_no")
    int altPhoneNo;
    @Column(name = "Role", columnDefinition = "INT(1) NOT NULL CHECK(role BETWEEN 0  AND 2) COMMENT 'funkcja: 0 - klient, 1 - dostawca, 2 - oba'")
    int role;
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(String apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    public int getPostal() {
        return postal;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getAltPhoneNo() {
        return altPhoneNo;
    }

    public void setAltPhoneNo(int altPhoneNo) {
        this.altPhoneNo = altPhoneNo;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Customer(long customerId, String customerName, String street, String buildingNo, String apartmentNo, int postal, String city, String country, int phoneNo, int altPhoneNo, int role) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.street = street;
        this.buildingNo = buildingNo;
        this.apartmentNo = apartmentNo;
        this.postal = postal;
        this.city = city;
        this.country = country;
        this.phoneNo = phoneNo;
        this.altPhoneNo = altPhoneNo;
        this.role = role;
    }
}
