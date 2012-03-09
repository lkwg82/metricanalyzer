package de.lgohlke.io;

import java.io.File;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import com.thoughtworks.qdox.model.JavaClass;

import de.lgohlke.CommonStore;
import de.lgohlke.PicoContainerFactory;
import de.lgohlke.AST.ASTMetrics;
import de.lgohlke.AST.Registry;
import de.lgohlke.io.bo.TestClass;
import de.lgohlke.qdox.QDoxScanner;
import de.lgohlke.qdox.QDoxTestScanHandler;

/**
 * <p>
 * RelatedTestsFinderTest class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class RelatedTestsFinderTest
{
  private final MutablePicoContainer pico    = PicoContainerFactory.createContainer();
  private final File                 baseDir = new File("src/test/resources");
  private RelatedTestsFinder         finder;

  /**
   * <p>
   * setup.
   * </p>
   * 
   * @throws java.lang.Exception
   *           if any.
   */
  @Before
  public void setup() throws Exception
  {
    MetricDef[] metrics = new MetricDef[] { Metric.COMPLEXITY, ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE, ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION, };

    AstProcessor processor = new AstProcessor(pico);
    processor.addDirectories(baseDir);
    processor.init();
    processor.scan();

    // SourceProject prj =
    processor.decorateTreeWithMetrics(metrics);

    QDoxTestScanHandler testScanHandler = new QDoxTestScanHandler();
    new QDoxScanner(new Registry()).addScanHandler(testScanHandler).scan(baseDir);

    CommonStore store = pico.getComponent(CommonStore.class);
    for (JavaClass clazz : testScanHandler.getClassList())
    {
      store.getJavaClasses().add(clazz);
    }
    for (TestClass clazz : testScanHandler.getTestClasses())
    {
      store.getTestClasses().add(clazz);
    }

    Registry registry = processor.getRegistry();

    finder = new RelatedTestsFinder(pico, registry.getLocationList(),registry);finder.find();
  }

  /**
   * <p>
   * findRelatedTest.
   * </p>
   * 
   * @throws java.lang.Exception
   *           if any.
   */
  @Test
  public void findRelatedTest() throws Exception
  {
    String key = "RelatedTests/TestTestClass#test()V";

    TestmethodContext qr = TestmethodContextFactory.createByKey(finder,key);

    //    System.out.println(qr);

    Assert.assertEquals(5, qr.getRelatedMethods().size());
  }
}
