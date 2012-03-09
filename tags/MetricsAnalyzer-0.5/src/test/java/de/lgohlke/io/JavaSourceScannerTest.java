package de.lgohlke.io;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.picocontainer.MutablePicoContainer;

import de.lgohlke.CommonStore;
import de.lgohlke.PicoContainerFactory;
import de.lgohlke.AST.Registry;

/**
 * <p>
 * JavaTestClassFinderTest class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class JavaSourceScannerTest
{

  private MutablePicoContainer pico;
  private JavaSourceScanner    finder;

  @Before
  public void setup()
  {
    pico = PicoContainerFactory.createContainer();
    finder = new JavaSourceScanner(pico, new Registry());
  }

  @Test
  public void testAbsolute() throws IOException
  {
    finder.addDirs(new File(System.getProperty("user.dir")));
    finder.scan();
  }

  @Test
  public void testRelativePath() throws IOException
  {
    finder.addDirs(new File("src"));
    finder.scan();
  }

  @Test
  public void testRelativePath2() throws IOException
  {
    finder.addDirs(new File("src/test/java"));
    finder.scan();
  }

  @After
  public void afterTest()
  {
    CommonStore store = pico.getComponent(CommonStore.class);
    Assert.assertTrue("test classes should be more than one", store.getTestClasses().size() > 1);
  }

  // @Test
  /**
   * <p>
   * testFullScan.
   * </p>
   * 
   * @throws java.io.IOException
   *           if any.
   */
  public void testFullScan() throws IOException
  {
    JavaSourceScanner finder = new JavaSourceScanner(pico, new Registry());
    finder.addDirs(new File("/home/lars/Downloads/sonar-source/sonar-plugins"));
    finder.scan();

    CommonStore store = pico.getComponent(CommonStore.class);
    // for (TestClass clazz : finder.getTestClasses())
    // {
    // System.out.println(clazz);
    // }
    Assert.assertTrue("test classes should be more than one", store.getTestClasses().size() > 1);
  }
}
