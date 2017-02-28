package com.domain.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphanumericInputValidation implements Validation {

    public static final Pattern ALPHANUMERIC_REGEX =
            Pattern.compile("^[a-zA-Z0-9]*$", Pattern.CASE_INSENSITIVE);

    private String input;

    public AlphanumericInputValidation(String input) {
        this.input = input;
    }

    @Override
    public boolean isValid() {
        if (input != null){
            Matcher matcher = ALPHANUMERIC_REGEX.matcher(input);
            if (matcher != null)
                return matcher.find();
        }
        return false;
    }
}
