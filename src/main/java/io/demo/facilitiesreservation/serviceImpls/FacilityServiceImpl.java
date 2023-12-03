package io.demo.facilitiesreservation.serviceImpls;

import java.util.ArrayList;

import io.demo.facilitiesreservation.entities.Facility;
import io.demo.facilitiesreservation.services.FacilityService;

public class FacilityServiceImpl implements FacilityService {

    @Override
    public Facility createFacility(Facility facility) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFacility'");
    }

    @Override
    public Facility getFacility(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFacility'");
    }

    @Override
    public ArrayList<Facility> getAllFacility() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllFacility'");
    }

    @Override
    public ArrayList<Facility> getAvailable(String dateFrom, String dateTo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailable'");
    }

    @Override
    public void doesReservationOverlap(Facility hotel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doesReservationOverlap'");
    }

    @Override
    public boolean validateFacilityExistenceById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateFacilityExistenceById'");
    }

    @Override
    public Facility updateFacility(Long id, Facility facility) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFacility'");
    }

    @Override
    public void deleteFacility(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFacility'");
    }

}
