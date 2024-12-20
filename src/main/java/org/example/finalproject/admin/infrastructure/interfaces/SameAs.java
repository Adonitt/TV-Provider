package org.example.finalproject.admin.infrastructure.interfaces;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.finalproject.admin.infrastructure.validators.SameAsValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SameAsValidator.class)
@Documented

public @interface SameAs {
    String message() default "Fields should match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field(); // Name of the field to compare with
}
