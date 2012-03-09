package de.lgohlke.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * wrapper class for fixedSizeThreadPool
 * 
 * @author lars
 */
public class ThreadPool
{
  private static final long     SLEEPTIME = 500;
  private final ExecutorService threadPool;

  public ThreadPool(final int maxcores)
  {
    this.threadPool = Executors.newFixedThreadPool(maxcores);
  }

  /**
   * creates an instance with 2*availableProcessores-Thread
   * 
   * @return
   */
  public static ThreadPool getInstance()
  {
    int cores = Runtime.getRuntime().availableProcessors();
    int maxThreads = cores * 2;
    return new ThreadPool(maxThreads);
  }

  public Future<?> submit(final Runnable task)
  {
    return threadPool.submit(task);
  }

  public void waitForShutdown()
  {
    threadPool.shutdown();
    while (!threadPool.isTerminated())
    {
      try
      {
        Thread.sleep(SLEEPTIME);
      }
      catch (InterruptedException e)
      {
        // ok
      }
    }
  }
}
