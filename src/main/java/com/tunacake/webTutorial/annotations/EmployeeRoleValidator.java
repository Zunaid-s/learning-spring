package com.tunacake.webTutorial.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {

//    @Override
//    public void initialize(EmployeeRoleValidation constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> roles = List.of("ADMIN", "USER", "MANAGER");
        return roles.contains(value);
    }
}
