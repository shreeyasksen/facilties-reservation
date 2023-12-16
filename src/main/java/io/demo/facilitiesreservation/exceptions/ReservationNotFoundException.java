package io.demo.facilitiesreservation.exceptions;

import java.util.UUID;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(UUID id) {
        super("Unable to find reservation with id: " + id);
    }

}
