package org.shop.customer;

import lombok.AllArgsConstructor;
import org.shop.dtos.UpdateCustomerDto;
import org.shop.entities.Customer;
import org.shop.exceptions.NotFoundException;
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
        Customer oldCustomer = getById(id).orElseThrow(() -> new NotFoundException("Id " + id + " does not exist"));
        if (customerDto.customerName() != null) oldCustomer.setCustomerName(customerDto.customerName());
        if (customerDto.nip() != null) oldCustomer.setNip(customerDto.nip());
        if (customerDto.street() != null) oldCustomer.setStreet(customerDto.street());
        if (customerDto.buildingNo() != null) oldCustomer.setBuildingNo(customerDto.buildingNo());
        if (customerDto.apartmentNo() != null) oldCustomer.setApartmentNo(customerDto.apartmentNo());
        if (customerDto.postal() != null) oldCustomer.setPostal(customerDto.postal());
        if (customerDto.city() != null) oldCustomer.setCity(customerDto.city());
        if (customerDto.country() != null) oldCustomer.setCountry(customerDto.country());
        if (customerDto.phoneNo() != null) oldCustomer.setPhoneNo(customerDto.phoneNo());
        if (customerDto.email() != null) oldCustomer.setEmail(customerDto.email());
        if (customerDto.role() != null) oldCustomer.setRole(customerDto.role());
        return repository.save(oldCustomer);
    }

    public Optional<Customer> getById(Long id) {
        return repository.findById(id);
    }

    public List<Customer> findCustomerByName(String customerName) {
        customerName = "%" + customerName + "%";
        return repository.findCustomerByName(customerName);
    }

    public List<Customer> findCustomerByNip(String customerNip) {
        customerNip = "%" + customerNip + "%";
        return repository.findCustomerByNip(customerNip);
    }
}
