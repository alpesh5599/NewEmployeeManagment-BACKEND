package com.nexscend.employee.management.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullValidator implements ConstraintValidator<NotNullField, Object> {
	
	@Override
    public void initialize(NotNullField constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return value != null && !value.toString().trim().isEmpty();
    }
}
