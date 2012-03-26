/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * marker annotation for problematic tests
 * 
 
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ METHOD })
public @interface HasProblemsWithParallelRuns {

}
