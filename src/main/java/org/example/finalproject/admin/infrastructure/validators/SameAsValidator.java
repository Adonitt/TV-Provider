package org.example.finalproject.admin.infrastructure.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.finalproject.admin.infrastructure.interfaces.SameAs;
import org.springframework.beans.BeanWrapperImpl;

public class SameAsValidator implements ConstraintValidator<SameAs, Object> {

    private String field;

    @Override
    public void initialize(SameAs constraintAnnotation) {
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null values
        }

        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);

        return value.equals(fieldValue);
    }
}