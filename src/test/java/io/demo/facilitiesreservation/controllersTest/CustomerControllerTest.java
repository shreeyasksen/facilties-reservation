import io.demo.facilitiesreservation.controllers.CustomerController;
import io.demo.facilitiesreservation.entities.Customer;
import io.demo.facilitiesreservation.entities.Reservation;
import io.demo.facilitiesreservation.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchCustomers() {
        // Arrange
        String name = "John";
        ArrayList<Customer> mockCustomers = new ArrayList<>();
        when(customerService.searchCustomers(name)).thenReturn(mockCustomers);

        // Act
        ResponseEntity<ArrayList<Customer>> responseEntity = customerController.searchCustomers(name);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCustomers, responseEntity.getBody());
        verify(customerService, times(1)).searchCustomers(name);
    }

    @Test
    void testCreateCustomer() {
        // Arrange
        Customer mockCustomer = new Customer();
        when(customerService.createCustomer(mockCustomer)).thenReturn(mockCustomer);

        // Act
        ResponseEntity<Customer> responseEntity = customerController.createCustomer(mockCustomer);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockCustomer, responseEntity.getBody());
        verify(customerService, times(1)).createCustomer(mockCustomer);
    }


}
