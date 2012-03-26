/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.pageobjects.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

/**
 * A class for doing a wait for sth.
 * 
 
 */
public class WaiterTest {

    class Sleeper implements Runnable {

        private final int sleep;

        public Sleeper(final int sleep) {
            this.sleep = sleep;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                // ok
            }
        }

        public Thread getRunningThread() {
            Thread thread = new Thread(this);
            thread.start();
            return thread;
        }
    };

    public void run(final int maxWait, final int sleep) {

        Waiter waiter = new Waiter(maxWait, TimeUnit.MILLISECONDS);
        final Thread thread = new Sleeper(sleep).getRunningThread();

        waiter.forCondition(new ICondition() {

            @Override
            public String getErrorMessage() {
                return null;
            }

            @Override
            public boolean check() {
                return !thread.isAlive();
            }
        });

    }

    @Test(groups = "helper")
    public void testSuccess() {
        run(500, 100);
    }

    @Test(groups = "helper", expectedExceptions = TimeoutException.class)
    public void testFail() {
        run(100, 300);
    }

}
