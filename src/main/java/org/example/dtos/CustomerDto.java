package org.example.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record CustomerDto(long customerId, String customerName, String street, String buildingNo, String apartmentNo,
                          int postal, String city, String country, int phoneNo, int altPhoneNo, @Min(0) @Max(2) int role) {
}
