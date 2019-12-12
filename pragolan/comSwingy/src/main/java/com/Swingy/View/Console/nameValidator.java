package com.Swingy.View.Console;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class nameValidator implements ConstraintValidator<NameValidate,
        Object> {

    @Override
    public void initialize(NameValidate isValidDate) {
        // TODO Auto-generated method stub
        System.out.println("Initialised");

    }
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        /*if (o == null)
            return false;
        String nameVal = o.toString();
        isValid = nameVal.length() > 3;*/
        System.out.println(constraintValidatorContext.buildConstraintViolationWithTemplate("Try again").addConstraintViolation());
        return false;
    }
}
