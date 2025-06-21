package com.tech.application.config.validator;

import com.tech.domain.membre.port.MembreDomain;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private MembreDomain membreDomain;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        // Initialization logic if needed
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return true; // Or false, depending on your null/empty email policy
        }
        // Check if a user with this email already exists in the database
        return !membreDomain.findByEmail(email);
    }
}