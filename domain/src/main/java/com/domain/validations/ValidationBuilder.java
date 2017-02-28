package com.domain.validations;

import java.util.ArrayList;
import java.util.List;

public class ValidationBuilder {

    List<Validation> validationList;

    private ValidationBuilder() {
        validationList = new ArrayList<>();
    }

    public static ValidationBuilder builder(){
        return new ValidationBuilder();
    }

    public ValidationBuilder addValidation(Validation validation){
        this.validationList.add(validation);
        return this;
    }

    public boolean isValid(){
        for(Validation validation : validationList){
            if (!validation.isValid()){
                return false;
            }
        }
        return true;
    }

}
