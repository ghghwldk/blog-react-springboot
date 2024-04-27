package com.m.blog.global.customAnnotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SecurityComponent {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
