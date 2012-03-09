package de.lgohlke.qdox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.lgohlke.AST.Registry;

/**
 * <p>QDoxScannerTest class.</p>
 *
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class QDoxScannerTest
{
  private QDoxScanner scanner;
  private QDoxTestScanHandler testScanHandler;

  @Before
  public void prepare(){

    scanner = new QDoxScanner(new Registry());
    testScanHandler  = new QDoxTestScanHandler();
    scanner.addScanHandler(testScanHandler);

  }
  /**
   * <p>testScanFile.</p>
   *
   * @throws java.io.FileNotFoundException if any.
   * @throws java.io.IOException if any.
   */
  @Test
  public void testScanFile() throws FileNotFoundException, IOException
  {
    File clazz = new File("src/test/resources/de/lgohlke/test/DummyClass.java");
    scanner.scan(clazz);
    Assert.assertEquals(1, testScanHandler.getTestClasses().size());

  }

  /**
   * <p>testScanDir.</p>
   *
   * @throws java.io.FileNotFoundException if any.
   * @throws java.io.IOException if any.
   */
  @Test
  public void testScanDir() throws FileNotFoundException, IOException
  {
    File clazz = new File("src/test");

    scanner.scan(clazz);
    Assert.assertTrue(testScanHandler.getTestClasses().size() > 10);
  }

  /**
   * <p>testScanDirWithNoTests.</p>
   *
   * @throws java.io.FileNotFoundException if any.
   * @throws java.io.IOException if any.
   */
  @Test
  public void testScanDirWithNoTests() throws FileNotFoundException, IOException
  {
    File clazz = new File("src/main");

    scanner.scan(clazz);
    Assert.assertTrue(testScanHandler.getTestClasses().size() == 0);
  }
}
