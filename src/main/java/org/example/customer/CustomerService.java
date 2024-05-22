package org.example.customer;

import lombok.AllArgsConstructor;
import org.example.dtos.UpdateCustomerDto;
import org.example.entities.Customer;
import org.example.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    // TODO: Wydzieliłbym interfejs tych metod, żeby było łatwiej testować.
    // Wtedy można by było zrobić implementację w stylu CustomerServiceImpl.
    // Sugerowałbym bym również w Serwisach zwracać DTO, a nie encje. Na przykładzie w CapWSB-Fitness Tracker tam zastosowałem skrót,
    // mappowanie z Entity-> jest po streonie Controllera - natomiast nie jest to dobra praktyka
    CustomerRepository repository;

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Customer updateAndSaveCustomer(Long id, UpdateCustomerDto customerDto) {
        Customer oldCustomer = getById(id).orElseThrow(() -> new NotFoundException("Id " + id + " does not exist"));
        // Jest ok, ale nie lepiej z Optionalem? Np.Optional.ofNullable(customerDto.customerName()).ifPresent(oldCustomer::setCustomerName);
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
