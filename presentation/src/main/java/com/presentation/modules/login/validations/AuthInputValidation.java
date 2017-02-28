package com.presentation.modules.login.validations;


import com.domain.validations.Validation;

import java.util.List;

public abstract class AuthInputValidation {

    public AuthInputValidation(){

    }

    public boolean isValid(){
        for(Validation validation : getValidations()){
            if (!validation.isValid()){
                return false;
            }
        }
        return true;
    }

    public abstract List<Validation> getValidations();
}
