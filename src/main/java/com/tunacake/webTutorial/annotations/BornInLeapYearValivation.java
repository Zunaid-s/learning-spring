package com.tunacake.webTutorial.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LeapYearValidator.class)
public @interface BornInLeapYearValivation {
    String message() default "Employee must be born in a leap year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
