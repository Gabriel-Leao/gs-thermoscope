package br.com.fiap.thermoscope.validations.conclusionDate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ConclusionDateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidConclusionDate {
    String message() default "The conclusion date can't be in the past.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
