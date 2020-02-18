package pl.coderslab.trucktrans.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class MinAgeValidator implements ConstraintValidator<MinAge, Integer> {

    private int minAge;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return false;
        }
        int currentYear = LocalDateTime.now().getYear();
        return  (currentYear - value) >= minAge;
    }

    @Override
    public void initialize(MinAge constraintAnnotation) {
        this.minAge = constraintAnnotation.value();

    }
}
