package io.demo.facilitiesreservation.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.demo.facilitiesreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

}
