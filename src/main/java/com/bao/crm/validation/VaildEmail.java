package com.bao.crm.validation;

import com.bao.crm.validation.Impl.VaildEmailImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = VaildEmailImpl.class)
@Retention(RUNTIME)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
public @interface VaildEmail {
    String message() default "Invalid email format";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
