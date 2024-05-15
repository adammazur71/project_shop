package org.example.dtos;

public record CustomerDto(long customerId, String customerName, String street, String buildingNo, String apartmentNo,
                          int postal, String city, String country, int phoneNo, int altPhoneNo, int role) {
}
