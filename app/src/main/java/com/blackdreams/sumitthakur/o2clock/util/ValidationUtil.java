package com.blackdreams.sumitthakur.o2clock.util;

import java.util.regex.Pattern;

/**
 * Created by sumitthakur on 25/07/18.
 */

public class ValidationUtil {

    private static final String REGEX_EMAIL_ADDRESS = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
            + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
            + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";
    private static final String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    private static final int PASSWORD_LENGTH = 8;
    private static final int MAX_PHONE_LENGTH = 12;
    private static final int MIN_PHONE_LENGTH = 8;


    /**
     * Method to validate email id
     *
     * @param email user email
     * @return whether email is valid
     */
    public static boolean checkEmail(final String email) {
        return !(email.trim().isEmpty() || (!email.matches(Pattern.compile(REGEX_EMAIL_ADDRESS).toString())));
    }


    /**
     * Method to validate phoneNumber Field
     *
     * @param phone instance of edit text
     * @return boolean
     */
    public static boolean checkPhoneNumber(final String phone) {
        String phoneNumber = phone.trim();
        if (phoneNumber.isEmpty()) {
            return false;
        }
        if (phoneNumber.contains(" ")) {
            return false;
        }
        if (Double.valueOf(phoneNumber) == 0) {
            return false;
        }
        if (phoneNumber.length() < PASSWORD_LENGTH
                || Long.valueOf(phoneNumber) == PASSWORD_LENGTH
                || phoneNumber.length() > MAX_PHONE_LENGTH
                || phoneNumber.length() < MIN_PHONE_LENGTH) {
            return false;
        }

        return true;
    }


    /**
     * Method to validate password
     *
     * @param password user entered password
     * @return whether the password is valid
     */
    public static boolean checkPassword(final String password) {
        if (password.isEmpty() || (password.length() < PASSWORD_LENGTH) | (!password.matches(REGEX_PASSWORD))) {
            return false;
        }
        return true;
    }

}
