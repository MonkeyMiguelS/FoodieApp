package com.domain.validations;

public class MaxLengthValidation implements Validation{

    private String input;
    private int maxLength;

    public MaxLengthValidation(String input, int maxLength) {
        this.input = input;
        this.maxLength = maxLength;
    }


    @Override
    public boolean isValid() {
        return (input != null && input.length() <= maxLength);
    }
}
