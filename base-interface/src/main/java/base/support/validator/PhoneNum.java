package base.support.validator;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ConstraintComposition(CompositionType.AND)
@Target({ METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNum {
    String message() default "핸드폰은 10자리, 11자리 숫자로만 입력해야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
