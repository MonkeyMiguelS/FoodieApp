package com.domain.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsciiInputValidation implements Validation {

    public static final Pattern ASCI_REGEX =
            Pattern.compile("^[a-zA-Z0-9._%+-@]*$", Pattern.CASE_INSENSITIVE);

    private String input;

    public AsciiInputValidation(String input) {
        this.input = input;
    }

    @Override
    public boolean isValid() {
        if (input != null){
            Matcher matcher = ASCI_REGEX.matcher(input);
            if (matcher != null)
                return matcher.find();
        }
        return false;
    }
}
