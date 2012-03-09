package de.lgohlke;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.api.SourceClass;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceFile;
import org.sonar.squid.api.SourcePackage;
import org.sonar.squid.api.SourceProject;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.Registry;
import de.lgohlke.analyzer.MetricsWorthToAnalyze;
import de.lgohlke.analyzer.RelatedCodeMetricAggregator;
import de.lgohlke.io.AstProcessor;
import de.lgohlke.io.AstProcessorException;
import de.lgohlke.syntaxhighlighter.CodeDocumentGenerator;
import de.lgohlke.syntaxhighlighter.CodeDocumentGeneratorFactory;

/**
 * <p>
 * MetricAnalyzer class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
@RequiredArgsConstructor
public final class MetricAnalyzer
{
  private static final String  ANALYZE_SOURCE_DIR_PARAM = "analyze.source.dir";
  private static final String  ANALYZE_OUTPUT_DIR_PARAM = "analyze.output.dir";
  @Getter
  private final List<File>     directoriesToAnalyse     = new ArrayList<File>();
  @Getter
  private File                 directoryToWriteTo;
  private AstProcessor         processor;

  private MutablePicoContainer pico                     = PicoContainerFactory.createContainer();

  // System.getProperty("user.dir") + "/src/test/java/de/lgohlke/io"
  // private final static String defaultAnalyzeDir =
  // "/home/lars/Downloads/sonar-source/sonar-git/sonar-squid/src/test/java/org/sonar/squid/api";

  // private final static String defaultAnalyzeDir =
  // "/home/lars/Downloads/sources/org.apache.squashfs.d/org.apche.activemq_trunk";
  // private final static String defaultAnalyzeDir =
  // "/home/lars/Downloads/sources/org.apache.squashfs.d/org.apache.camel_trunk/components/camel-jetty";
  private final static String defaultAnalyzeDir = System.getProperty("user.dir");
  //  private final static String  defaultAnalyzeDir        = "/home/lars/Downloads/sources/org.apache.squashfs.d/org.apache.camel_trunk/components/camel-bindy,/home/lars/Downloads/sources/org.apache.squashfs.d/org.apache.camel_trunk/components/camel-hdfs";
  //  private final static String defaultAnalyzeDir =  "/home/lars/Downloads/sources/compressed_source.commons.squashfs.d";
  /**
   * <p>
   * main.
   * </p>
   * 
   * @param args
   *          an array of {@link java.lang.String} objects.
   * @throws java.lang.InstantiationException
   *           if any.
   * @throws Exception
   */
  public static void main(final String[] args) throws Exception
  {
    File directoryToAnalyse = getParam(ANALYZE_SOURCE_DIR_PARAM, defaultAnalyzeDir);
    File directoryToWriteTo = getParam(ANALYZE_OUTPUT_DIR_PARAM, System.getProperty("user.dir") + "/target/analysis");

    MetricAnalyzer analyzer = new MetricAnalyzer();

    if (directoryToAnalyse.getAbsolutePath().contains(","))
    {
      String[] paths = directoryToAnalyse.getAbsolutePath().split("\\ *,\\ *");
      for (String path : paths)
      {
        analyzer.addDirectoryForAnalysis(new File(path));
      }
    }
    else
    {
      analyzer.addDirectoryForAnalysis(directoryToAnalyse);
    }

    long start = System.currentTimeMillis();

    analyzer.writeTo(directoryToWriteTo).analyze();
    long end = System.currentTimeMillis();
    log.info(String.format("finished after : %.2fs", (float) (end - start) / 1000));
  }

  public MetricAnalyzer writeTo(final File directoryToWriteTo2)
  {
    this.directoryToWriteTo = directoryToWriteTo2;
    return this;
  }

  public MetricAnalyzer addDirectoryForAnalysis(final File directoryToAnalyse2)
  {
    directoriesToAnalyse.add(directoryToAnalyse2);
    return this;
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

  private SourceProject processingAst() throws IOException, AstProcessorException
  {
    processor = new AstProcessor(pico);
    processor.init();
    List<MetricDef> metrics = new ArrayList<MetricDef>();

    metrics.addAll(MetricsWorthToAnalyze.LIST);
    //    metrics.addAll(Arrays.asList(Metric.values()));
    log.debug("starting adding directories to astprocessor");
    processor.addDirectories(directoriesToAnalyse.toArray(new File[directoriesToAnalyse.size()]));
    log.debug("finished adding directories to astprocessor");

    log.info("scanning (AST)");
    processor.scan();

    showStatistics(processor.getRegistry());

    log.info("decorating (AST)");
    return processor.decorateTreeWithMetrics(metrics.toArray(new MetricDef[metrics.size()]));
  }

  private static void showStatistics(final Registry registry)
  {
    log.info("------------------------------------");
    log.info("files       : " + registry.getIndexedFiles().size());
    // log.info("paths       : " + registry.getIndexedPaths().size());
    log.info("descriptions: " + registry.getDescription().size());
    log.info("metrics     : " + registry.getMetricPerLineMap().size());
    log.info("packages    : " + registry.getPackageName().size());
    log.info("------------------------------------");
  }

  public void analyze() throws Exception
  {
    SourceProject project = processingAst();

    new RelatedCodeMetricAggregator(pico, processor.getSquid(), processor.getRegistry().getLocationList(), processor.getRegistry()).doAggregation();

    printTestMethodStats(project);

    CodeDocumentGenerator codeDocumentGenerator = CodeDocumentGeneratorFactory.createDefaultInstance(pico, project, getDirectoryToWriteTo(),
        processor.getSquid());
    try
    {
      codeDocumentGenerator.generate(processor.getRegistry());
    }
    catch (IOException e)
    {
      log.error(e.getMessage() + "", e);
    }
    log.info("finished");
  }

  private static void printTestMethodStats(final SourceCode code)
  {
    if (code.hasChildren())
    {
      if ((code.isType(SourceProject.class) || code.isType(SourcePackage.class) || code.isType(SourceFile.class) || code.isType(SourceClass.class)))
      {
        for (SourceCode s : code.getChildren())
        {
          printTestMethodStats(s);
        }
      }
    }
  }
}
