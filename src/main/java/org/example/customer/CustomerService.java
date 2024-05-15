package org.example.customer;

import lombok.AllArgsConstructor;
import org.example.dtos.UpdateCustomerDto;
import org.example.entieties.Customer;
import org.example.exceptions.IdNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    CustomerRepository repository;

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Customer updateAndSaveCustomer(Long id, UpdateCustomerDto customerDto) {
        Customer oldCustomer = getById(id).orElseThrow(() -> new IdNotFoundException("Id " + id + " does not exist"));
        Customer.CustomerBuilder builder = Customer.builder();
        builder.customerId(id);
        if (customerDto.customerName() != null) oldCustomer.setCustomerName(customerDto.customerName());
        if (customerDto.nip() != null) oldCustomer.setNip(customerDto.nip());
        if (customerDto.street() != null) oldCustomer.setStreet(customerDto.street());
        if (customerDto.buildingNo() != null) oldCustomer.setBuildingNo(customerDto.buildingNo());
        if (customerDto.apartmentNo() != null) oldCustomer.setApartmentNo(customerDto.apartmentNo());
        if (customerDto.postal() != null) oldCustomer.setPostal(customerDto.postal());
        if (customerDto.city() != null) oldCustomer.setCity(customerDto.city());
        if (customerDto.country() != null) oldCustomer.setCountry(customerDto.country());
        if (customerDto.phoneNo() != null) oldCustomer.setPhoneNo(customerDto.phoneNo());
        if (customerDto.altPhoneNo() != null) oldCustomer.setAltPhoneNo(customerDto.altPhoneNo());
        if (customerDto.role() != null) oldCustomer.setRole(customerDto.role());
        Customer updatedCustomer = builder.build();
        return repository.save(updatedCustomer);
    }

    public Optional<Customer> getById(Long id) {
        return repository.findById(id);
    }

    public List<Customer> findCustomerByName(String customerName) {
        customerName = "%" + customerName + "%";
        return repository.findCustomerByName(customerName);
    }
}
