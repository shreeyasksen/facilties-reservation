package io.demo.facilitiesreservation.controllersTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.demo.facilitiesreservation.controllers.FacilityController;
import io.demo.facilitiesreservation.entities.Facility;
import io.demo.facilitiesreservation.services.FacilityService;

class FacilityControllerTest {

    @Mock
    private FacilityService facilityService;

    @InjectMocks
    private FacilityController facilityController;

    // This method initializes the mocks before each test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFacility() {
        // Arrange
        ArrayList<Facility> allFacility = new ArrayList<>(); // make new facility

        // Configure the mock behavior
        when(facilityService.getAllFacility()).thenReturn(allFacility);

        // Act
        ResponseEntity<ArrayList<Facility>> response = facilityController.getAllFacility();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allFacility, response.getBody());
        verify(facilityService, times(1)).getAllFacility();
    }

    @Test
    void testGetFacility() {
        // Arrange
        Long facilityId = 1L;
        Facility foundFacility = new Facility(facilityId, null, null, null, null, null, null, false);

        // Configure the mock behavior
        when(facilityService.getFacility(facilityId)).thenReturn(foundFacility);

        // Act
        ResponseEntity<Facility> response = facilityController.getFacility(facilityId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foundFacility, response.getBody());
        verify(facilityService, times(1)).getFacility(facilityId);
    }

}
