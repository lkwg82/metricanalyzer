/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ METHOD })
public @interface HandledInPageObject {

    Class<?> clazz() default String.class;

    String method();

    /**
     * maybe somethimes we need a hint
     * 
     * @return
     */
    String hint() default "";

}
