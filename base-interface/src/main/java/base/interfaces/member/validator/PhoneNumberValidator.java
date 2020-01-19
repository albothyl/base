package base.interfaces.member.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNum, String> {


    @Override
    public void initialize(PhoneNum constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }

        if(value.length() == 10 || value.length() == 11) {
            Matcher matcher = Pattern.compile("[0-9]+").matcher(value);

            return matcher.matches();
        }

        return false;
    }
}
