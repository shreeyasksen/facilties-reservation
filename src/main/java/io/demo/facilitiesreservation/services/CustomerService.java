package io.demo.facilitiesreservation.services;

import java.util.ArrayList;
import java.util.UUID;

import io.demo.facilitiesreservation.entities.Customer;
import io.demo.facilitiesreservation.entities.Reservation;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    Customer getCustomer(UUID id);

    ArrayList<Customer> getAllCustomers();

    Customer updateCustomer(UUID id, Customer customer);

    void deleteCustomer(UUID id);

    Reservation addReservation(UUID id, Reservation reservation);

    ArrayList<Customer> searchCustomers(String name);

}
