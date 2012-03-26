package biz.company.qa.common;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.company.qa.reporting.Test;
import biz.company.qa.reporting.Tests;
import biz.company.qa.testutils.TestSpecification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class ResultListener implements ITestListener {

    private final static Logger log = LoggerFactory.getLogger(ResultListener.class);
    private final String name = "ResultListener";
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    @Override
    public void onStart(ITestContext context) {
        // ok
    }

    @Override
    public void onFinish(ITestContext context) {
        // ok
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // ok
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String tname = result.getTestClass().getName() + ":" + result.getName();
        String message = "FAILED executing test method '" + tname + "'";

        Throwable ex = result.getThrowable();
        if (ex != null) {
            if (!(ex instanceof AssertionError)) {
                log.info(message + " with error: " + result.getThrowable().getMessage());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ex.printStackTrace(new PrintStream(out));
                log.info("Stack Trace: " + out.toString());
            } else {
                StackTraceElement[] trace = ex.getStackTrace();
                StackTraceElement e = null;
                int i = 0;
                do {
                    e = trace[i++];
                } while (e.getClassName().startsWith("org.testng"));
                log.info(message + " with error: " + result.getThrowable().getMessage() + " at " + e.getClassName()
                        + "." + e.getMethodName() + ":" + e.getLineNumber());
            }
        } else {
            log.info(message + " with no exception");
        }

        Test test = new Test();
        test = result2Test(result, test);
        test.setResult("FAILURE");
        test.setNotes(result.getThrowable().getMessage());
        Tests.getInstance().add(test);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String name = result.getTestClass().getName() + ":" + result.getName();
        String message = "SKIPPED test method '" + name + "'";

        // add exception message if possible
        if (result.getThrowable() != null) {
            message = message + " with error: " + result.getThrowable().getMessage();
        }

        log.info(message);

        Test test = new Test();
        test = result2Test(result, test);
        test.setResult("SKIPPED");
        test.setNotes("This test was skipped");
        Tests.getInstance().add(test);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String tname = result.getTestClass().getName() + ":" + result.getName();
        String parameters = "(";
        for (int i = 0; i < result.getParameters().length; i++) {
            parameters += result.getParameters()[i];
            if (i != (result.getParameters().length - 1)) {
                parameters += ", ";
            }
        }
        parameters += ")";

        log.info("Starting test method [" + Thread.currentThread().getId() + "]: " + tname + " " + parameters);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Test test = new Test();
        test = result2Test(result, test);
        test.setResult("SUCCESS");

        if (Tests.getInstance().add(test)) {
            String tname = result.getTestClass().getName() + ":" + result.getName();
            log.info("Successfully finished test method: " + tname);
        }
    }

    /**
     * This private method fills a Test object with all relevant information it gets from the result
     */
    private Test result2Test(ITestResult result, Test test) {
        Date now = new Date();
        String[] bugs = new String[0];
        String[] requirements = new String[0];
        String id = "";

        test.setName(result.getName());
        test.setClassname(result.getTestClass().getName());
        test.setDate(dateFormat.format(now));

        ITestNGMethod method = result.getMethod();
        Annotation[] annotations = method.getMethod().getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof TestSpecification) {
                TestSpecification spec = (TestSpecification) annotation;
                bugs = spec.bugs();
                requirements = spec.requirements();
                id = spec.id();
            }
        }

        for (String bug : bugs) {
            test.addBug(bug);
        }

        for (String requirement : requirements) {
            test.addRequirement(requirement);
        }

        if (!id.isEmpty()) {
            test.setId(id);
        }

        return test;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResultListener other = (ResultListener) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
