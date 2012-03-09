package de.lgohlke.io;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.PicoContainerFactory;
import de.lgohlke.AST.ASTMetrics;

/**
 * <p>
 * AstProcessorTest class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class AstProcessorTest
{
  private MutablePicoContainer pico;
  private AstProcessor         processor;

  private final MetricDef[]    metrics = new MetricDef[] { Metric.COMPLEXITY, ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE,
      ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION, };

  /**
   * <p>
   * setup.
   * </p>
   */
  @Before
  public void setup()
  {
    pico = PicoContainerFactory.createContainer();
    processor = new AstProcessor(pico);
  }

  /**
   * <p>
   * testProcess.
   * </p>
   * 
   * @throws java.lang.Exception
   *           if any.
   */
  @Test //(timeout=20*1000)
  public void testProcess() throws Exception
  {

    File subDir = new File("src/main/java");

    processor.addDirectories(subDir);
    processor.init();
    processor.scan();

    // SourceCode prj =
    processor.decorateTreeWithMetrics(metrics);

    // System.out.println("Project type distance: " + prj.getInt(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));
    // System.out.println("Project complexity:" + prj.getInt(Metric.COMPLEXITY));
  }

  /**
   * <p>
   * testProcessEmptydirList.
   * </p>
   * 
   * @throws de.lgohlke.io.AstProcessorException
   *           if any.
   */
  @Test(expected = AstProcessorException.class)
  public void testProcessEmptydirList() throws AstProcessorException
  {
    processor.init();
    processor.scan();
  }
}
