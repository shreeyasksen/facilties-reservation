package io.demo.facilitiesreservation.serviceImplsTest;

import io.demo.facilitiesreservation.entities.Facility;
import io.demo.facilitiesreservation.entities.Reservation;
import io.demo.facilitiesreservation.repositories.FacilityRepository;
import io.demo.facilitiesreservation.repositories.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private FacilityRepository facilityRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    // This method initializes the mocks before each test
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateReservationExistenceById_ValidId() {
        // Arrange
        UUID reservationId = UUID.randomUUID();
        when(reservationRepository.existsById(reservationId)).thenReturn(true);

        // Act
        boolean result = reservationService.validateReservationExistenceById(reservationId);

        // Assert
        assertTrue(result);
    }

    @Test
    void testValidateReservationExistenceById_InvalidId() {
        // Arrange
        UUID invalidReservationId = UUID.randomUUID();
        when(reservationRepository.existsById(invalidReservationId)).thenReturn(false);

        // Act
        boolean result = reservationService.validateReservationExistenceById(invalidReservationId);

        // Assert
        assertFalse(result);
    }

}
