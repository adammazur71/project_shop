package org.example.customer;

import org.example.dtos.UpdateCustomerDto;
import org.example.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer save(Customer customer);

    Customer updateAndSaveCustomer(Long id, UpdateCustomerDto customerDto);

    Optional<Customer> getById(Long id);

    List<Customer> findCustomerByName(String customerName);

    List<Customer> findCustomerByNip(String customerNip);
}
