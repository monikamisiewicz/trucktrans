package pl.coderslab.trucktrans.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Constraint(validatedBy = MinAgeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinAge {

    int value();

    String message() default "{dateOfBirth.error.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
