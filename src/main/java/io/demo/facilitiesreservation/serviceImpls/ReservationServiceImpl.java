package io.demo.facilitiesreservation.serviceImpls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, FacilityRepository facilityRepository) {
        this.reservationRepository = reservationRepository;
        this.facilityRepository = facilityRepository;
    }

    // Check if Reservation exists
    // TODO InvalidRequestExpcetion
    @Override
    public boolean validateReservationExistenceById(UUID id) {
        return reservationRepository.existsById(id);
    }

    // TODO - thorw expection
    @Override
    public boolean validateFacilityExistenceById(Long id) {
        if (!facilityRepository.existsById(id)) {
            return false;
        } else if (facilityRepository.findById(id).get().getAvailableFrom() == null
                && facilityRepository.findById(id).get().getAvailableTo() == null)
            return false;
        else {
            return true;
        }
    }

    // TODO - throw exception
    @Override
    public boolean dateIsBefore(String date1, String date2) {
        try {
            return sdf.parse(date1).before(sdf.parse(date2));
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        Long reservationFacilityId = reservation.getFacility().getId();

        // Determine if reservation is valid with existence of reservationId
        if (validateFacilityExistenceById(reservationFacilityId)) {
            // Determine if reservation overlaps with existing reservations
            if (reservationOverlaps(reservation)) {
                return reservation;
            }
            if (dateIsBefore(facilityRepository.findById(reservation.getFacility().getId()).get().getAvailableFrom(),
                    reservation.getCheckIn())
                    && dateIsBefore(reservation.getCheckOut(),
                            facilityRepository.findById(reservation.getFacility().getId()).get().getAvailableTo())) {
                reservation = reservationRepository.save(reservation);
            } else {
                return reservation;
            }

            return reservation;

        }
        return reservation;

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

    @Override
    public void deleteReservation(UUID id) {
        validateReservationExistenceById(id);
        reservationRepository.deleteById(id);
    }

    // TODO - throw exception and debug
    @Override
    public boolean reservationOverlaps(Reservation reservation) {
        return reservationRepository.findAll().stream().anyMatch(results -> {
            if (results.getFacility().getId() == reservation.getFacility().getId()) {
                try {
                    int checkInBeforeDbCheckOut = sdf.parse(reservation.getCheckIn())
                            .compareTo(sdf.parse(results.getCheckOut()));
                    int checkOutBeforeDbCheckIn = sdf.parse(reservation.getCheckOut())
                            .compareTo(sdf.parse(results.getCheckIn()));

                    if (checkInBeforeDbCheckOut == 0 || checkOutBeforeDbCheckIn == 0) {
                        return true;
                    } else {
                        return checkInBeforeDbCheckOut != checkOutBeforeDbCheckIn;
                    }

                } catch (ParseException e) {
                    return false;
                }

            } else {
                return false;
            }
        });
    }

}
