package com.domain.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailInputValidation implements Validation {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private String input;

    public EmailInputValidation(String input) {
        this.input = input;
    }

    @Override
    public boolean isValid() {
        if (input != null){
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(input);
            if (matcher != null)
                return matcher.find();
        }
        return false;
    }
}
