/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;

/**
 * A class for doing a wait for sth.
 * 
 
 */
public class Waiter {

    private static final long LOOP_RECHECK_WAITING_TIME = 200L;
    private final long timeout;

    /**
     * @param timeout - unit count
     * @param unit - which unit ss/ms/ns/hours
     */
    public Waiter(final int timeout, final TimeUnit unit) {

        this.timeout = TimeUnit.MILLISECONDS.convert(timeout, unit);
    }

    /**
     * @param condition
     */
    public final void forCondition(final ICondition condition) {
        long waitTime = LOOP_RECHECK_WAITING_TIME;
        long maxRounds = timeout / waitTime;

        boolean isOk = false;
        for (int round = 0; round < maxRounds && !isOk; round++) {
            isOk = condition.check();

            if (!isOk) {
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    // ok
                }
            }
        }

        if (!isOk) {
            throw new TimeoutException("timeout exceeded : " + condition.getErrorMessage());
        }

    }

}
