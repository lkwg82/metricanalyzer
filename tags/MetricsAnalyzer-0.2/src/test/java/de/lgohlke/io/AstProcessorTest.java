package de.lgohlke.io;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.ASTMetrics;

public class AstProcessorTest
{
  private AstProcessor processor;
  private final File   baseDir = new File(System.getProperty("user.dir"));

  @Before
  public void setup()
  {
    processor = new AstProcessor();
  }

  @Test
  public void testProcess() throws Exception
  {
    MetricDef[] metrics = new MetricDef[]
    { Metric.COMPLEXITY, ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE, ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION, };

    File subDir = new File(baseDir.getAbsolutePath() + "/src");

    processor.addDirectories(baseDir, subDir);
    processor.init();
    processor.scan();

    // SourceCode prj =
    processor.decorateTreeWithMetrics(metrics);

    // System.out.println("Project type distance: " + prj.getInt(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));
    // System.out.println("Project complexity:" + prj.getInt(Metric.COMPLEXITY));
  }
}
