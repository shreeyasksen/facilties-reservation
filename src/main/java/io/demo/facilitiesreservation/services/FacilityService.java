package io.demo.facilitiesreservation.services;

import java.util.ArrayList;

import io.demo.facilitiesreservation.entities.Facility;

public interface FacilityService {

    Facility createFacility(Facility facility);

    Facility getFacility(Long id);

    ArrayList<Facility> getAllFacility();

    ArrayList<Facility> getAvailable(String dateFrom, String dateTo);

    void doesReservationOverlap(Facility hotel);

    boolean validateFacilityExistenceById(Long id);

    Facility updateFacility(Long id, Facility facility);

    void deleteFacility(Long id);
}
