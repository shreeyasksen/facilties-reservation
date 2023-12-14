import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        Facility foundFacility = new Facility(); // Create a facility here

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
