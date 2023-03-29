package com.lab.rabbit.util;

import com.lab.rabbit.Exceptions.ExceptionInvalidEmail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    private final static String INVALID_EMAIL = "El correo remitente no es valido por favor rectifique.";

    private final static String REGEX_VALID_EMAIL = "^(.+)@(.+)$";
    public static void emailValid(String email) {
        Pattern pattern = Pattern.compile(REGEX_VALID_EMAIL);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ExceptionInvalidEmail(INVALID_EMAIL);
        }
    }
}
