package de.lgohlke.syntaxhighlighter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.sonar.squid.api.SourceProject;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.ASTMetrics;
import de.lgohlke.AST.Registry;
import de.lgohlke.io.AstProcessor;
import de.lgohlke.io.JavaTestClassFinder;

public class CodeDocumentGeneratorTest
{
  @Test
  public void testCreation() throws Exception
  {
    JavaTestClassFinder finder = new JavaTestClassFinder();
    finder.getPathList().add(System.getProperty("user.dir"));
    finder.scan();

    File resourcePath = new File("src/main/resources/syntaxhighlighter_3.0.83");
    AstProcessor processor = new AstProcessor();
    List<MetricDef> metrics = new ArrayList<MetricDef>();
    metrics.addAll(Arrays.asList(ASTMetrics.values()));
    metrics.addAll(Arrays.asList(Metric.values()));
    processor.addDirectories(new File(System.getProperty("user.dir")));
    processor.init();
    processor.scan();

    SourceProject project = processor.decorateTreeWithMetrics(metrics.toArray(new MetricDef[metrics.size()]));

    CodeDocumentGenerator documentator = new CodeDocumentGenerator(finder, project);
    documentator.setResourcePath(resourcePath);
    documentator.setTargetDirectory(new File("target/docs"));
    documentator.generate(new Registry());

  }
}
