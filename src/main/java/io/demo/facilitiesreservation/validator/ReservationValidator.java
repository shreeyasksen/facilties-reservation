package io.demo.facilitiesreservation.validator;

import io.demo.facilitiesreservation.entities.Reservation;
import io.demo.facilitiesreservation.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReservationValidator extends BaseValidator {

    public static void validateGuests(Integer guests) {
        if (guests == null || guests <= 0) {
            log.error("{} is an invalid number.", guests);
            throw new InvalidRequestException(null);
        }
    }

    public static void validateReservationPOST(Reservation reservation) {
        validateDates(reservation.getCheckIn(), reservation.getCheckOut());
        validateGuests(reservation.getGuests());
    }

}
