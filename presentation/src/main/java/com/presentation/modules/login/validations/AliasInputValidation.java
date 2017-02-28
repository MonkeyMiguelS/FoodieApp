package com.presentation.modules.login.validations;

import com.domain.validations.AsciiInputValidation;
import com.domain.validations.EmptyInputValidation;
import com.domain.validations.MaxLengthValidation;
import com.domain.validations.Validation;

import java.util.ArrayList;
import java.util.List;

public class AliasInputValidation extends AuthInputValidation {

    public static int MAX_LENGHT_ALIAS = 40;

    private String input;

    private AliasInputValidation(String input){
        this.input = input;
    }

    public static AliasInputValidation build(String input){
        return new AliasInputValidation(input);
    }

    @Override
    public List<Validation> getValidations() {
        List<Validation> validations = new ArrayList<>();
        validations.add(new EmptyInputValidation(input));
        validations.add(new MaxLengthValidation(input, MAX_LENGHT_ALIAS));
        validations.add(new AsciiInputValidation(input));
        return validations;
    }
}
