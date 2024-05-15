package org.example.customer;

import lombok.AllArgsConstructor;
import org.example.entieties.Customer;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    CustomerRepository repository;
    public Customer save(Customer customer) {
        return repository.save(customer);
    }
}
