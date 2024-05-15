package org.example.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CustomerDto(Long customerId, String customerName, String nip, String street, String buildingNo,
                          String apartmentNo,
                          Integer postal, String city, String country, String phoneNo, String altPhoneNo,
                          @Min(0) @Max(2) @NotNull Integer role) {
}
