package io.demo.facilitiesreservation.serviceImplsTest;

import io.demo.facilitiesreservation.entities.Facility;
import io.demo.facilitiesreservation.entities.Reservation;
import io.demo.facilitiesreservation.repositories.FacilityRepository;
import io.demo.facilitiesreservation.repositories.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacilityServiceImplTest {

    @Mock
    private FacilityRepository facilityRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private FacilityServiceImpl facilityService;

    // This method initializes the mocks before each test
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFacility() {
        // Arrange
        Facility facility = new Facility();
        when(facilityRepository.save(facility)).thenReturn(facility);

        // Act
        Facility result = facilityService.saveFacility(facility);

        // Assert
        assertNotNull(result);
        verify(facilityRepository, times(1)).save(facility);
    }

    @Test
    void testGetFacility_ValidId() {
        // Arrange
        Long facilityId = 1L;
        when(facilityRepository.findById(facilityId)).thenReturn(java.util.Optional.of(new Facility()));

        // Act
        Facility result = facilityService.getFacility(facilityId);

        // Assert
        assertNotNull(result);
        verify(facilityRepository, times(1)).findById(facilityId);
    }

    @Test
    void testGetFacility_InvalidId() {
        // Arrange
        Long invalidFacilityId = 2L;
        when(facilityRepository.findById(invalidFacilityId)).thenReturn(java.util.Optional.empty());

        // Act and Assert
        assertThrows(Exception.class, () -> facilityService.getFacility(invalidFacilityId));
    }

}
