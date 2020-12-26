package com.testproject;

import com.testproject.models.Customer;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String phoneRegex = "[+]\\d{5,12}+";

    public static boolean emailValidation(String email) {

        if (!EmailValidator.getInstance().isValid(email)) {
            return false;
        } else return email.length() <= 100;
    }

    public static boolean nameValidation(String name) {

        return name.length() > 1 && name.length() < 51;
    }

    public static boolean phoneValidation(String phone) {

        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    public static boolean customerValidation(Customer customer) {
        return nameValidation(customer.getFull_name()) &&
                emailValidation(customer.getEmail()) &&
                phoneValidation(customer.getPhone());
    }


}
