package io.demo.facilitiesreservation.services;

import java.util.ArrayList;
import java.util.UUID;

import io.demo.facilitiesreservation.entities.Reservation;

public interface ReservationService {

    // Reservation createReservation(Reservation reservation);

    Reservation getReservation(UUID id);

    ArrayList<Reservation> getAllReservation();

    Reservation saveReservation(Reservation reservation);

    void deleteReservation(UUID id);

    boolean validateFacilityExistenceById(Long id);

    boolean dateIsBefore(String date1, String date2);

    boolean reservationOverlaps(Reservation reservation);

    boolean validateReservationExistenceById(UUID id);
}
