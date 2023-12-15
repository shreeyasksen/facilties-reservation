package io.demo.facilitiesreservation.serviceImpls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import io.demo.facilitiesreservation.entities.Facility;
import io.demo.facilitiesreservation.entities.Reservation;
import io.demo.facilitiesreservation.repositories.FacilityRepository;
import io.demo.facilitiesreservation.repositories.ReservationRepository;
import io.demo.facilitiesreservation.services.FacilityService;

@Service
public class FacilityServiceImpl implements FacilityService {

    private FacilityRepository facilityRepository;
    private ReservationRepository reservationRepository;

    public FacilityServiceImpl(FacilityRepository facilityRepository, ReservationRepository reservationRepository) {
        this.facilityRepository = facilityRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Facility saveFacility(Facility facility) {
        if (facility.getAvailableFrom().isEmpty() && facility.getAvailableTo().isEmpty()) {
            facility.setAvailableFrom(null);
            facility.setAvailableTo(null);
        }
        return facilityRepository.save(facility);

    }

    // TODO throw exception
    @Override
    public Facility getFacility(Long id) {
        validateFacilityExistenceById(id);
        return facilityRepository.findById(id).orElseThrow();
    }

    @Override
    public ArrayList<Facility> getAllFacility() {
        List<Facility> allFacility = facilityRepository.findAll();
        return (ArrayList<Facility>) allFacility;
    }

    // Returns all facility objects that are available in between dateFrom and
    // dataTo parameters
    @Override
    public ArrayList<Facility> getAvailable(String dateFrom, String dateTo) {
        List<Facility> availableFacility = facilityRepository.findAllBetweenDates(dateFrom, dateTo);
        return (ArrayList<Facility>) availableFacility;
    }

    @Override
    public void doesReservationOverlap(Facility facility) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String availFrom = facility.getAvailableFrom();
        String availTo = facility.getAvailableTo();
        Long facilityId = facility.getId();
        List<Reservation> matchReservationList = reservationRepository.findAll().stream().filter(reservations -> {
            if (Objects.equals(reservations.getFacility().getId(), facilityId)) {
                try {
                    int checkInBeforeAvailFrom = sdf.parse(reservations.getCheckIn()).compareTo(sdf.parse(availFrom));
                    int checkInBeforeAvailTo = sdf.parse(reservations.getCheckOut()).compareTo(sdf.parse(availTo));
                    if ((checkInBeforeAvailFrom < 0) || (checkInBeforeAvailTo > 0)) {
                        return true;
                    }
                } catch (ParseException e) {
                    return false;

                }
            }
            return false;
        }).toList();
        if (matchReservationList.isEmpty()) {
            System.err.println("TODO");
        }
    }

    // TODO invalid request exception
    @Override
    public boolean validateFacilityExistenceById(Long id) {
        return facilityRepository.existsById(id);
    }

    @Override
    public Facility updateFacility(Facility facility) {
        validateFacilityExistenceById(facility.getId());
        doesReservationOverlap(facility);
        return facilityRepository.save(facility);
    }

    @Override
    public void deleteFacility(Long id) {
        validateFacilityExistenceById(id);
        if (reservationRepository.findAll().stream()
                .anyMatch(reservations -> reservations.getFacility().getId().equals(id))) {
            System.err.println("TODO");
        }
        facilityRepository.deleteById(id);
    }

}
