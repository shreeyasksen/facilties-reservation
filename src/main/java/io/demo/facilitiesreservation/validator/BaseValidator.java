package io.demo.facilitiesreservation.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import io.demo.facilitiesreservation.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseValidator {

    private static final String VALID_DATE_FORMAT = "yyyy-MM-dd";
    private static final DateFormat simpleDateFormat = new SimpleDateFormat(VALID_DATE_FORMAT);

    public static void validateID(Long id) {
        if (id == 0) {
            throw new InvalidRequestException(id);
        }
    }
}
