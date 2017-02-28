package com.domain.validations;

public class MinLengthValidation implements Validation{

    private String input;
    private int minLength;

    public MinLengthValidation(String input, int minLength) {
        this.input = input;
        this.minLength = minLength;
    }


    @Override
    public boolean isValid() {
        return (input != null && input.length() >= minLength);
    }
}
