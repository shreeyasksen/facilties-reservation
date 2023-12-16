package io.demo.facilitiesreservation.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.demo.facilitiesreservation.entities.Facility;
import io.demo.facilitiesreservation.services.FacilityService;
import io.demo.facilitiesreservation.validator.FacilityValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/facility")
public class FacilityController {

    private FacilityService facilityService;

    // @Autowired
    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    // READ all facility
    @GetMapping("")
    public ResponseEntity<ArrayList<Facility>> getAllFacility() {
        ArrayList<Facility> allFacility = facilityService.getAllFacility();
        return new ResponseEntity<>(allFacility, HttpStatus.OK);
    }

    // READ facility by id
    @GetMapping("/{id}")
    public ResponseEntity<Facility> getFacility(@PathVariable Long id) {
        FacilityValidator.validateId(id);
        log.info("Retrieve faciliy by id = {}", id);
        Facility foundFacility = facilityService.getFacility(id);
        return new ResponseEntity<>(foundFacility, HttpStatus.OK);
    }

    // READ facility by availability
    @GetMapping("/availabilitySearch")
    public ResponseEntity<ArrayList<Facility>> getFacilityAvailability(@RequestParam("dateFrom") String dateFrom,
            @RequestParam("dateTo") String dateTo) {
        FacilityValidator.validateDates(dateFrom, dateTo);
        log.info("Retrieve facility availability between dates from: {} to {}", dateFrom, dateTo);
        ArrayList<Facility> availableFacility = facilityService.getAvailable(dateFrom, dateTo);
        return new ResponseEntity<>(availableFacility, HttpStatus.OK);
    }

    // POST new Facility
    @PostMapping(produces = "application/json")
    public ResponseEntity<Facility> createFacility(@Valid @RequestBody Facility facility) {
        FacilityValidator.validateFacilityPOST(facility);
        log.info("New Facility added: {}", facility);
        Facility newFacility = facilityService.saveFacility(facility);
        return new ResponseEntity<>(newFacility, HttpStatus.CREATED);
    }

    // PUT new Facility
    @PutMapping("{id}")
    public ResponseEntity<Facility> updateFacility(@PathVariable Long id, @RequestBody Facility facility) {
        FacilityValidator.validateFacilityPUT(facility);
        log.info("Facility {} updated.", facility);
        Facility updatedFacility = facilityService.updateFacility(facility);

        return new ResponseEntity<>(updatedFacility, HttpStatus.OK);
    }

    // Delete Facility
    @DeleteMapping("{id}")
    public ResponseEntity<Facility> deleteFacility(@PathVariable Long id, @RequestBody Facility facility) {
        FacilityValidator.validateId(id);
        log.info("Facility {} deleted.", facility);
        facilityService.deleteFacility(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
