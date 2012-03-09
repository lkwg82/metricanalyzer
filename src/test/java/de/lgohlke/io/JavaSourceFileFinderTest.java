package de.lgohlke.io;

import org.junit.Assert;
import org.junit.Test;

public class JavaSourceFileFinderTest
{
  @Test
  public void testScanner()
  {
    System.setProperty("rootPath", System.getProperty("user.dir"));
    String rootPath = System.getProperty("rootPath");

    JavaSourceFileFinder finder = new JavaSourceFileFinder();
    finder.getPathList().add(rootPath);
    finder.scan();

    Assert.assertTrue("must be greater than one", finder.getSourceList().size() >= 1);
  }
}
