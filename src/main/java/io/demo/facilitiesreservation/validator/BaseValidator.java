package io.demo.facilitiesreservation.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

import io.demo.facilitiesreservation.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseValidator {

    private static final String VALID_DATE_FORMAT = "yyyy-MM-dd";

    private static final DateFormat simpleDateFormat = new SimpleDateFormat(VALID_DATE_FORMAT);

    public static void validateId(Long id) {
        if (id == 0) {
            throw new InvalidRequestException(id);
        }
    }

    public static void validateName(String name) {
        if (!StringUtils.hasText(name)) {
            log.error("Please enter name.");
            throw new InvalidRequestException(null);
        }
    }

    public static boolean validateDateFormat(String date) {
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(date);
        } catch (ParseException e) {
            log.error("Invalid date format: '{}', please input dates in 'yyyy-MM-dd' format.", date);
            throw new InvalidRequestException(null);
        }
        return true;
    }

    public static void validateDates(String startDate, String endDate) {
        // Checks to see if the dates are non-null and both dates are filled
        if (startDate == null || endDate == null) {
            throw new InvalidRequestException(null);
        }
        if (validateDateFormat(startDate) && validateDateFormat(endDate)) {
            try {
                if (simpleDateFormat.parse(startDate).after(simpleDateFormat.parse(endDate))) {
                    log.error("Start date: '{}' must be before end date: '{}'", startDate, endDate);
                    throw new InvalidRequestException(null);
                }
            } catch (ParseException e) {
                log.debug("Invalid date comparsion");
            }
        } else {
            log.error("Invalid date: Please input dates in 'yyyy-MM-dd' format.");
            throw new InvalidRequestException(null);
        }
    }
}
