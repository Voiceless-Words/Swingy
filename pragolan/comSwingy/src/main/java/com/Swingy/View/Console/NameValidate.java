package com.Swingy.View.Console;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Documented

@Constraint(validatedBy = { })
public @interface NameValidate{
    String message() default "{com.Swingy.View.Console.NameValidate.message}";

    Class<?>[] groups() default {};
    String value();

    Class<? extends Payload>[] payload() default {};
}
