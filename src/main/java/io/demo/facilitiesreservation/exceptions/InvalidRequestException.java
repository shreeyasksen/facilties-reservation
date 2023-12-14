package io.demo.facilitiesreservation.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(Long id) {
        super(id + "does not exist.");
    }
}
