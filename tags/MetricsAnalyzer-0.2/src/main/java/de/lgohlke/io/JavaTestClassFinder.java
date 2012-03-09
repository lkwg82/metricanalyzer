package de.lgohlke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.lgohlke.concurrent.ThreadPool;
import de.lgohlke.io.bo.TestClass;
import de.lgohlke.qdox.QDoxScanner;

public class JavaTestClassFinder
{
  private static Logger         log         = LoggerFactory.getLogger(JavaTestClassFinder.class);
  private final List<String>    pathList    = new ArrayList<String>();
  private final List<TestClass> testClasses = new ArrayList<TestClass>();

  public List<String> getPathList()
  {
    return pathList;
  }

  public void addDirs(final File... files)
  {
    for (File f : files)
    {
      pathList.add(f.getAbsolutePath());
    }
  }

  public void scan()
  {
    Set<File> files = findFiles();
    scanParallel(files);
  }

  private void scanParallel(final Set<File> files)
  {
    log.info("scanning code");

    ThreadPool threadPool = ThreadPool.getInstance();

    for (File file : files)
    {
      threadPool.submit(getScanJob(file, testClasses));
    }
    threadPool.waitForShutdown();

  }

  private Runnable getScanJob(final File file, final List<TestClass> results)
  {
    return new Runnable()
    {
      public void run()
      {
        QDoxScanner scanner = new QDoxScanner(file);
        try
        {
          scanner.scan();
        }
        catch (FileNotFoundException e)
        {
          log.error(e.getMessage(), e);
        }
        catch (IOException e)
        {
          log.error(e.getMessage(), e);
        }
        for (TestClass clazz : scanner.getClasses())
        {
          results.add(clazz);
        }
      }
    };

  }

  private Set<File> findFiles()
  {
    log.info("parsing directory tree");
    JavaSourceFileFinder finder = new JavaSourceFileFinder();
    finder.getPathList().addAll(pathList);
    finder.scan();

    return finder.getSourceList();
  }

  public List<TestClass> getTestClasses()
  {
    return testClasses;
  }
}
