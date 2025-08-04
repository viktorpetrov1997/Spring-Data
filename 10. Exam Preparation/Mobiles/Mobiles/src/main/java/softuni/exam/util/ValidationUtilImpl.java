package softuni.exam.util;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtilImpl implements ValidationUtil
{
    private final Validator validator;

    public ValidationUtilImpl()
    {
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory())
        {
            validator = factory.getValidator();
        }
    }

    @Override
    public <E> boolean isValid(E entity)
    {
        return validator.validate(entity).isEmpty();
    }
}
