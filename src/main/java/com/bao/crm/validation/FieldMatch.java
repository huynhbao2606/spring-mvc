package com.bao.crm.validation;


import com.bao.crm.validation.Impl.FieldMatchImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = FieldMatchImpl.class)
@Retention(RUNTIME)
@Target({TYPE, ANNOTATION_TYPE})
public @interface FieldMatch {
    String message() default "The password musm match";
    String first();
    String second();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Retention(RUNTIME)
    @Target({TYPE, ANNOTATION_TYPE})
    @interface List
    {
        FieldMatch[] value();
    }
}
