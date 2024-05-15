package org.example.customer;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dtos.CustomerDto;
import org.example.dtos.UpdateCustomerDto;
import org.example.entieties.Customer;
import org.example.exceptions.IdNotFoundException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {
    CustomerService service;
    CustomerMapper mapper;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Customer> saveNewCustomer(@RequestBody @Valid CustomerDto customerDto,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        Customer newCustomer = service.save(mapper.toEntity(customerDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    }

    @PatchMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody @Valid UpdateCustomerDto customerDto,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        Customer updatedCustomer = service.updateAndSaveCustomer(id, customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCustomer);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        Customer customerbyId = service.getById(id).orElseThrow(() -> new IdNotFoundException("Id " + id + " does not exist"));
        return ResponseEntity.ok(customerbyId);
    }

    @GetMapping(value = "/search/{customerName}", produces = "application/json")
    public ResponseEntity<List<CustomerDto>> findCustomerByName(@PathVariable String customerName) {
        List<Customer> customerlist = service.findCustomerByName(customerName);
        if (customerlist.size() == 0) {
            throw new NotFoundException("Customer name containing: " + customerName + " is not found");
        } else {
            List<CustomerDto> result = customerlist.stream()
                    .map(CustomerMapper::toDto)
                    .toList();
            return ResponseEntity.ok(result);
        }
    }

}
