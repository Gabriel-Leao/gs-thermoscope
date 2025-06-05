package br.com.fiap.thermoscope.validations.conclusionDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ConclusionDateValidator implements ConstraintValidator<ValidConclusionDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return !value.isBefore(LocalDate.now());
    }
}
