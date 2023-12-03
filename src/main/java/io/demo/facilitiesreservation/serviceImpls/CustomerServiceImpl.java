package io.demo.facilitiesreservation.serviceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.demo.facilitiesreservation.entities.Customer;
import io.demo.facilitiesreservation.entities.Reservation;
import io.demo.facilitiesreservation.repositories.CustomerRepository;
import io.demo.facilitiesreservation.repositories.ReservationRepository;
import io.demo.facilitiesreservation.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ReservationRepository reservationRepository;

    // @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ReservationRepository reservationRepository) {
        this.customerRepository = customerRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ArrayList<Customer> searchCustomers(String name) {
        List<Customer> foundCustomers = customerRepository.findByName(name);
        return (ArrayList<Customer>) foundCustomers;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // TODO Not Found Exception
    @Override
    public Customer getCustomer(UUID id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return (ArrayList<Customer>) allCustomers;
    }

    // TODO Not Found Exception
    @Override
    public Customer updateCustomer(UUID id, Customer customer) {

        Customer customerToUpdate = customerRepository.findById(id).orElseThrow();

        customerToUpdate.setName(customer.getName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setContactNo(customer.getContactNo());
        return customerRepository.save(customerToUpdate);
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }

    // TODO - throw Not Found exception
    @Override
    public Reservation addReservation(UUID id, Reservation reservation) {
        Customer selectedCustomer = customerRepository.findById(id).orElseThrow();

        reservation.setCustomer(selectedCustomer);
        return reservationRepository.save(reservation);
    }
}
