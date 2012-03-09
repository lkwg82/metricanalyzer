package de.lgohlke.qdox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class QDoxScannerTest
{
  @Test
  public void testScan() throws FileNotFoundException, IOException
  {
    File clazz = new File("src/test/resources/de/lgohlke/test/DummyClass.java");

    QDoxScanner scanner = new QDoxScanner(clazz);
    scanner.scan();
    Assert.assertEquals(1, scanner.getClasses().size());

    // for (TestClass _clazz : scanner.getClasses())
    // {
    // System.out.println(_clazz);
    // }
  }
}
