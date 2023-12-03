package io.demo.facilitiesreservation.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.demo.facilitiesreservation.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    // Custom query to find all customers with a certain first name
    List<Customer> findByName(String name);
}
