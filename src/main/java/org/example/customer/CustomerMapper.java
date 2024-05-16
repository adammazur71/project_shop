package org.example.customer;

import org.example.dtos.CustomerDto;
import org.example.entieties.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toEntity(CustomerDto customerDto) {
        return new Customer(customerDto.customerId(), customerDto.customerName(), customerDto.nip(), customerDto.street(), customerDto.buildingNo(),
                customerDto.apartmentNo(), customerDto.postal(), customerDto.city(), customerDto.country(), customerDto.phoneNo(),
                customerDto.email(), customerDto.role());
    }

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(customer.getCustomerId(), customer.getCustomerName(), customer.getNip(),
                customer.getStreet(), customer.getBuildingNo(), customer.getApartmentNo(), customer.getPostal(),
                customer.getCity(), customer.getCountry(), customer.getPhoneNo(), customer.getEmail(), customer.getRole());
    }
}
