package io.demo.facilitiesreservation.serviceImpls;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.demo.facilitiesreservation.entities.Reservation;
import io.demo.facilitiesreservation.repositories.FacilityRepository;
import io.demo.facilitiesreservation.repositories.ReservationRepository;
import io.demo.facilitiesreservation.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private FacilityRepository facilityRepository;

    // @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, FacilityRepository facilityRepository) {
        this.reservationRepository = reservationRepository;
        this.facilityRepository = facilityRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // TODO InvalidRequestExpcetion
    @Override
    public boolean validateReservationExistenceById(UUID id) {
        return reservationRepository.existsById(id);
    }

    // TODO - throw NOT Found exception
    @Override
    public Reservation getReservation(UUID id) {
        validateReservationExistenceById(id);
        return reservationRepository.findById(id).orElseThrow();
    }

    @Override
    public ArrayList<Reservation> getAllReservation() {
        return (ArrayList<Reservation>) reservationRepository.findAll();
    }

}
