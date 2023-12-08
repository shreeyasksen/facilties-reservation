package io.demo.facilitiesreservation.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.demo.facilitiesreservation.entities.Facility;
import io.demo.facilitiesreservation.services.FacilityService;

@RestController
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

    @GetMapping("/{id}")
    public ResponseEntity<Facility> getFacility(@PathVariable Long id) {
        Facility foundFacility = facilityService.getFacility(id);
        return new ResponseEntity<>(foundFacility, HttpStatus.OK);
    }

}
