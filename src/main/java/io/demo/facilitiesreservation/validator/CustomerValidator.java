package io.demo.facilitiesreservation.validator;

import io.demo.facilitiesreservation.entities.Customer;

public class CustomerValidator extends BaseValidator {

    public static void validateCustomerPOST(Customer customer) {
        validateName(customer.getName());
    }

    public static void validateCustomerPUT(Customer customer) {
        validateName(customer.getName());
    }
}
