package org.example.customer;

import org.example.entieties.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customers WHERE customer_name like(:customerName)", nativeQuery = true)
    List<Customer> findCustomerByName(@Param("customerName") String customerName);



    @Query(value = "SELECT * FROM customers WHERE nip like(:customerNip)", nativeQuery = true)
    List<Customer> findCustomerByNip(@Param("customerNip") String customerName);
}
