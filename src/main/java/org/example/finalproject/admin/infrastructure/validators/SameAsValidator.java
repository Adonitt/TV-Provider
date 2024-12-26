package org.example.finalproject.admin.infrastructure.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.finalproject.admin.infrastructure.interfaces.SameAs;
import org.springframework.beans.BeanWrapperImpl;

public class SameAsValidator implements ConstraintValidator<SameAs, Object> {

    private String field;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null values
        }

        // Get the value of the field (password)
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);

        // Get the value of the confirmPassword field
        Object confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue("confirmPassword");

        // Ensure both fields are non-null and compare them
        return fieldValue != null && confirmPasswordValue != null && fieldValue.equals(confirmPasswordValue);
    }

}
