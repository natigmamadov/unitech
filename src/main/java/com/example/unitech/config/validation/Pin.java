package com.example.unitech.config.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.*;

import static com.example.unitech.model.constants.ApplicationConstants.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({FIELD, TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
@NotBlank(message = PIN_MUST_NOT_BE_EMPTY)
public @interface Pin {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}