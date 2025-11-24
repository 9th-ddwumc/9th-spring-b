package com.example.jpa_practice.global.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface PositivePage {

    /**
     * Query parameter name. Defaults to the method parameter name.
     */
    String value() default "";

    /**
     * Default page number to use when param is missing.
     */
    int defaultValue() default 1;
}

