package com.tunacake.webTutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class LeapYearValidator implements ConstraintValidator<BornInLeapYearValivation, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        int bornyear = LocalDate.now().getYear() - value;
        if(bornyear % 100 == 0 && bornyear % 400 == 0) {
            System.out.println("Employee Born in leap year");
            return true;
        }
        else if(bornyear % 4 == 0 && bornyear % 100 != 0) {
            System.out.println("Employee Born in leap year");
            return true;
        }
        return true;

    }
}
