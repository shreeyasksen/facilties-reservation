package io.demo.facilitiesreservation.serviceImplsTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.demo.facilitiesreservation.entities.Facility;
import io.demo.facilitiesreservation.repositories.FacilityRepository;
import io.demo.facilitiesreservation.repositories.ReservationRepository;
import io.demo.facilitiesreservation.serviceImpls.FacilityServiceImpl;

class FacilityServiceImplTest {

    @Mock
    private FacilityRepository facilityRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    FacilityServiceImpl facilityService;

    // This method initializes the mocks before each test
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFacility() {
        // Arrange
        Facility facility = new Facility(null, null, null, null, null, null, null, false);
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
        when(facilityRepository.findById(facilityId))
                .thenReturn(java.util.Optional.of(new Facility(facilityId, null, null, null, null, null, null, false)));

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
