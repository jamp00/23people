package people.io.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = RutValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RutNumber {

    String message() default "Invalid rut format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
