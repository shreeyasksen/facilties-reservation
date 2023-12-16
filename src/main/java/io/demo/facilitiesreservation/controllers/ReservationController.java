package io.demo.facilitiesreservation.controllers;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.demo.facilitiesreservation.entities.Reservation;
import io.demo.facilitiesreservation.exceptions.ReservationNotFoundException;
import io.demo.facilitiesreservation.services.ReservationService;
import io.demo.facilitiesreservation.validator.ReservationValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;

    // @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // CREATE
    @PostMapping("")
    public ResponseEntity<Reservation> createReservation(@RequestBody @Valid Reservation reservation) {
        ReservationValidator.validateReservationPOST(reservation);
        log.info("Save new reservation.");
        Reservation newReservation = reservationService.saveReservation(reservation);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping("")
    public ResponseEntity<ArrayList<Reservation>> getAllReservations() {
        ArrayList<Reservation> allReservations = reservationService.getAllReservation();
        return new ResponseEntity<>(allReservations, HttpStatus.OK);
    }

    // READ (GET ONE)
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable UUID id) {
        try {
            Reservation foundReservation = reservationService.getReservation(id);
            return new ResponseEntity<>(foundReservation, HttpStatus.OK);
        } catch (ReservationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable UUID id) {
        try {
            reservationService.deleteReservation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ReservationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Reservation
    // @PutMapping("/{id}")
    // public ResponseEntity<Reservation> updateReservation(@PathVariable UUID id,
    // @RequestBody Reservation reservation) {
    // ReservationValidator.validateId(id);
    // log.info("Updating reservation.");
    // Reservation updatedReservation = reservationService.updateReservation(id,
    // reservation);
    // return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    // }

}
