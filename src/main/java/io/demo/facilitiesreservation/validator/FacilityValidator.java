package io.demo.facilitiesreservation.validator;

import java.util.Arrays;

import io.demo.facilitiesreservation.entities.Facility;
import io.demo.facilitiesreservation.entities.types.ValidTypesOfFaciltiesEnum;
import io.demo.facilitiesreservation.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FacilityValidator extends BaseValidator {

    public static void validateType(ValidTypesOfFaciltiesEnum type) {
        if (type == null || !Arrays.asList("SPORTS", "LEISURE", "BUSINESS").contains(type.toString())) {
            log.error("{} is invalid. Choose only from the following: Sports, Leisure, Business", type);
            throw new InvalidRequestException(null);
        }
    }

    public static void validateFacilityPOST(Facility facility) {
        validateName(facility.getName());
        validateType(facility.getType());
        validateDates(facility.getAvailableFrom(), facility.getAvailableTo());
    }

    public static void validateFacilityPUT(Facility facility) {
        validateId(facility.getId());
        validateName(facility.getName());
        validateType(facility.getType());
        validateDates(facility.getAvailableFrom(), facility.getAvailableTo());
    }
}
