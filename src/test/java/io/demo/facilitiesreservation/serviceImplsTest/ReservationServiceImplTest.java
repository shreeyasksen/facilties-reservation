package io.demo.facilitiesreservation.serviceImplsTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.demo.facilitiesreservation.repositories.FacilityRepository;
import io.demo.facilitiesreservation.repositories.ReservationRepository;
import io.demo.facilitiesreservation.serviceImpls.ReservationServiceImpl;

class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private FacilityRepository facilityRepository;

    @InjectMocks
    ReservationServiceImpl reservationService;

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
