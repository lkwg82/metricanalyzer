package de.lgohlke.io.bo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import de.lgohlke.AST.Registry;
import de.lgohlke.qdox.QDoxScanner;
import de.lgohlke.qdox.QDoxTestScanHandler;

/**
 * <p>TEST_TYPETest class.</p>
 *
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class TEST_TYPETest
{
  /**
   * <p>testDection.</p>
   *
   * @throws java.io.FileNotFoundException if any.
   * @throws java.io.IOException if any.
   */
  @Test
  public void testDection() throws FileNotFoundException, IOException
  {
    File clazz = new File("src/test/resources/de/lgohlke/test/DummyClass.java");

    QDoxScanner scanner = new QDoxScanner(new Registry());
    QDoxTestScanHandler testScanHandler = new QDoxTestScanHandler();
    scanner.addScanHandler(testScanHandler);
    scanner.scan(clazz);

    TestMethod first = testScanHandler.getTestClasses().get(0).getTests().get(0);
    // TestMethod second = scanner.getClasses().get(0).getTests().get(1);

    Assert.assertEquals(TEST_TYPE.JUNIT, first.getType());
    // Assert.assertEquals(TEST_TYPE.TESTNG, second.getType());
  }
}
