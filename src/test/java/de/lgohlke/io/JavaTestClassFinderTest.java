package de.lgohlke.io;

import org.junit.Assert;
import org.junit.Test;

public class JavaTestClassFinderTest
{
  @Test
  public void testTestFinder()
  {
    JavaTestClassFinder finder = new JavaTestClassFinder();
    finder.getPathList().add(System.getProperty("user.dir"));
    finder.scan();

    Assert.assertTrue("test classes should be more than one", finder.getTestClasses().size() > 1);
  }

  // @Test
  public void testFullScan()
  {
    JavaTestClassFinder finder = new JavaTestClassFinder();
    finder.getPathList().add("/home/lars/Downloads/sonar-source/sonar-plugins");
    finder.scan();

    // for (TestClass clazz : finder.getTestClasses())
    // {
    // System.out.println(clazz);
    // }
    Assert.assertTrue("test classes should be more than one", finder.getTestClasses().size() > 1);
  }
}
