/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.process;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * declares the classes which are accepted as input
 * 
 
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ TYPE })
public @interface AcceptedInput {

    /**
     * accepted process
     * 
     * @return
     */
    Class<? extends IProcess>[] types();

}
