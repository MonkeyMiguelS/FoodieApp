package com.presentation.modules.login.validations;


import com.domain.validations.AlphanumericInputValidation;
import com.domain.validations.EmptyInputValidation;
import com.domain.validations.MaxLengthValidation;
import com.domain.validations.MinLengthValidation;
import com.domain.validations.Validation;

import java.util.ArrayList;
import java.util.List;

public class PassInputValidation extends AuthInputValidation {

    public static int MAX_LENGHT_PASSWORD = 20;
    public static int MIN_LENGHT_PASSWORD = 8;

    private String input;

    private PassInputValidation(String input){
        this.input = input;
    }

    public static PassInputValidation build(String input){
        return new PassInputValidation(input);
    }

    @Override
    public List<Validation> getValidations() {
        List<Validation> validations = new ArrayList<>();
        validations.add(new EmptyInputValidation(input));
        validations.add(new MaxLengthValidation(input, MAX_LENGHT_PASSWORD));
        validations.add(new MinLengthValidation(input, MIN_LENGHT_PASSWORD));
        validations.add(new AlphanumericInputValidation(input));
        return validations;
    }
}
