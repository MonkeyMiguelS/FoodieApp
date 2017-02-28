package com.domain.validations;

public class EmptyInputValidation implements Validation {

    private String input;

    public EmptyInputValidation(String input) {
        this.input = input;
    }

    @Override
    public boolean isValid() {
        return (input != null
                    && input.toString().trim().length() > 0
                    && !input.isEmpty());
    }
}
