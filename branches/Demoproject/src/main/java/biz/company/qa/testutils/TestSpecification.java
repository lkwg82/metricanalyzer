/*
 * $Id$
 * (C) 2001 - 2008 company.biz AG
 */ 
package biz.company.qa.testutils;

import java.lang.annotation.*;

/**
 * Specifies a test case.
 *
 
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestSpecification {

    /**
     * The id of the testcase.
     * Ideally id's are managed by the test management tool.
     */
    String id() default "";
    
    /**
     * The id's of the requirements that are covered by this test.
     */
    String[] requirements() default {};
    
    /**
     * The id's of the bugs that are covered by this test.
     */
    String[] bugs() default {};
    
}
