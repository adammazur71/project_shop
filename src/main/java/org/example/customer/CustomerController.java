package org.example.customer;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dtos.CustomerDto;
import org.example.dtos.UpdateCustomerDto;
import org.example.entieties.Customer;
import org.example.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {
    CustomerService service;
    CustomerMapper mapper;

    @PostMapping("/")
    public ResponseEntity<Customer> saveNewCustomer(@RequestBody @Valid CustomerDto customerDto,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        Customer newCustomer = service.save(mapper.toEntity(customerDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    }
    @PatchMapping("/id")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody @Valid UpdateCustomerDto customerDto,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        Customer updatedCustomer = service.updateAndSaveCustomer(id, customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCustomer);

    }
}
