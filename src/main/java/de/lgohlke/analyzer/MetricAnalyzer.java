package de.lgohlke.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.squid.api.SourceClass;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceFile;
import org.sonar.squid.api.SourceMethod;
import org.sonar.squid.api.SourcePackage;
import org.sonar.squid.api.SourceProject;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.ASTMetrics;
import de.lgohlke.AST.Registry;
import de.lgohlke.io.AstProcessor;
import de.lgohlke.io.JavaTestClassFinder;
import de.lgohlke.syntaxhighlighter.CodeDocumentGenerator;
import de.lgohlke.syntaxhighlighter.CodeDocumentGeneratorFactor;

public final class MetricAnalyzer
{
  private final static Logger log                      = LoggerFactory.getLogger(MetricAnalyzer.class);
  private static final String ANALYZE_SOURCE_DIR_PARAM = "analyze.source.dir";
  private static final String ANALYZE_OUTPUT_DIR_PARAM = "analyze.output.dir";
  private final File          directoryToAnalyse;
  private final File          directoryToWriteTo;

  private MetricAnalyzer(final File directoryToAnalyse, final File directoryToWriteTo)
  {
    this.directoryToAnalyse = directoryToAnalyse;
    this.directoryToWriteTo = directoryToWriteTo;
  }

  private static AstProcessor processor;

  /**
   * @param args
   * @throws Exception
   */
  public static void main(final String[] args)
  {
    File directoryToAnalyse = getParam(ANALYZE_SOURCE_DIR_PARAM, System.getProperty("user.dir") + "/src/test/java/de/lgohlke/io");
    File directoryToWriteTo = getParam(ANALYZE_OUTPUT_DIR_PARAM, System.getProperty("user.dir") + "/target/analyzation");

    new MetricAnalyzer(directoryToAnalyse, directoryToWriteTo).analyze();
  }

  private static File getParam(final String key, final String defaultValue)
  {
    if (System.getProperties().keySet().contains(key) && (System.getProperty(key).length() > 0))
    {
      return new File(System.getProperty(key));
    }
    else
    {
      return new File(defaultValue);
    }
  }

  private SourceProject processingAst()
  {
    try
    {
      processor = new AstProcessor();
      List<MetricDef> metrics = new ArrayList<MetricDef>();

      metrics.addAll(Arrays.asList(ASTMetrics.values()));
      metrics.addAll(Arrays.asList(Metric.values()));
      processor.addDirectories(getDirectoryToAnalyse());
      processor.init();

      log.info("scanning (AST)");
      processor.scan();

      showStatistics(processor.getRegistry());

      log.info("decorating (AST)");
      return processor.decorateTreeWithMetrics(metrics.toArray(new MetricDef[metrics.size()]));
    }
    catch (Exception e)
    {
      log.error(e.getMessage(), e);
    }
    return null;
  }

  private static void showStatistics(final Registry registry)
  {
    log.info("------------------------------------");
    log.info("files       : " + registry.getIndexedFiles().size());
    log.info("paths       : " + registry.getIndexedPaths().size());
    log.info("descriptions: " + registry.getDescription().size());
    log.info("metrics     : " + registry.getMetricPerLineMap().size());
    log.info("packages    : " + registry.getPackageName().size());
    log.info("------------------------------------");
  }

  private void analyze()
  {

    // assume SLF4J is bound to logback in the current environment
    new LogbackHelper().configure();

    SourceProject project = processingAst();

    // System.out.println(project.getDouble((MetricDef) Metric.COMPLEXITY));
    // System.out.println(project.getDouble(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION));
    // System.out.println(project.getDouble(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));

    JavaTestClassFinder finder = new JavaTestClassFinder();
    finder.addDirs(getDirectoryToAnalyse());
    log.info("running finder scan()");
    finder.scan();

    printTestMethodStats(project);

    CodeDocumentGenerator codeDocumentGenerator = CodeDocumentGeneratorFactor.createDefaultInstance(finder, project, getDirectoryToWriteTo());
    try
    {
      codeDocumentGenerator.generate(processor.getRegistry());
    }
    catch (IOException e)
    {
      log.error(e.getMessage(), e);
    }
    log.info("finished");

  }

  private static void printTestMethodStats(final SourceCode code)
  {
    if (code.getChildren() != null)
    {
      if (code.isType(SourceProject.class))
      {
        for (SourceCode s : code.getChildren())
        {
          printTestMethodStats(s);
        }
      }
      else if (code.isType(SourcePackage.class))
      {
        for (SourceCode s : code.getChildren())
        {
          printTestMethodStats(s);
        }
      }
      else if (code.isType(SourceFile.class))
      {
        for (SourceCode s : code.getChildren())
        {
          printTestMethodStats(s);
        }
      }
      else if (code.isType(SourceClass.class))
      {
        for (SourceCode s : code.getChildren())
        {
          printTestMethodStats(s);
        }
      }
      else if (code.isType(SourceMethod.class))
      {
        // System.out.println(code);
      }
    }
  }

  public File getDirectoryToAnalyse()
  {
    return directoryToAnalyse;
  }

  public File getDirectoryToWriteTo()
  {
    return directoryToWriteTo;
  }
}
