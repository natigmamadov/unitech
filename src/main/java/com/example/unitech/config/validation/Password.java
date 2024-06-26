package com.example.unitech.config.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

import static com.example.unitech.model.constants.ApplicationConstants.PASSWORD_MUST_NOT_BE_BLANK;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({FIELD, TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Size(min = 6)
@NotBlank(message = PASSWORD_MUST_NOT_BE_BLANK)
public @interface Password {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}