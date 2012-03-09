package de.lgohlke.io.bo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import de.lgohlke.qdox.QDoxScanner;

public class TEST_TYPETest
{
  @Test
  public void testDection() throws FileNotFoundException, IOException
  {
    File clazz = new File("src/test/resources/de/lgohlke/test/DummyClass.java");

    QDoxScanner scanner = new QDoxScanner(clazz);
    scanner.scan();

    TestMethod first = scanner.getClasses().get(0).getTests().get(0);
    // TestMethod second = scanner.getClasses().get(0).getTests().get(1);

    Assert.assertEquals(TEST_TYPE.JUNIT, first.getType());
    // Assert.assertEquals(TEST_TYPE.TESTNG, second.getType());
  }
}
