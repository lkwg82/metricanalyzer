package de.lgohlke;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.picocontainer.MutablePicoContainer;
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
  @Getter
  private final List<File>           directoriesToAnalyse = new ArrayList<File>();
  @Getter
  private File                       directoryToWriteTo;
  private AstProcessor               processor;

  @Getter
  private AnalysisTestFilter         analysisTestFilter;
  private final MutablePicoContainer pico                 = PicoContainerFactory.createContainer();

  // System.getProperty("user.dir") + "/src/test/java/de/lgohlke/io"
  // private final static String defaultAnalyzeDir =
  // "/home/lars/Downloads/sonar-source/sonar-git/sonar-squid/src/test/java/org/sonar/squid/api";

  // private final static String defaultAnalyzeDir =
  // "/home/lars/Downloads/sources/org.apache.squashfs.d/org.apche.activemq_trunk";
  // private final static String defaultAnalyzeDir =
  // "/home/lars/Downloads/sources/org.apache.squashfs.d/org.apache.camel_trunk/components/camel-jetty";
  // private final static String defaultAnalyzeDir =
  // "/home/lars/Downloads/sources/org.apache.squashfs.d/org.apache.camel_trunk/components/camel-bindy,/home/lars/Downloads/sources/org.apache.squashfs.d/org.apache.camel_trunk/components/camel-hdfs";
  // private final static String defaultAnalyzeDir =
  // "/home/lars/Downloads/sources/compressed_source.commons.squashfs.d";

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

  private SourceProject processingAst() throws IOException, AstProcessorException
  {
    processor = new AstProcessor(pico);
    processor.init();
    List<MetricDef> metrics = new ArrayList<MetricDef>();

    metrics.addAll(MetricsWorthToAnalyze.LIST);
    // metrics.addAll(Arrays.asList(Metric.values()));
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
    if ((analysisTestFilter != null) && analysisTestFilter.isActive() && (analysisTestFilter.getFilter().getFailedTests().size() <= 1))
    {
      // no need for any analysis
      log.info("filter tells me the size of tests found are 0 or 1, so I stop here");
    }
    else
    {
      SourceProject project = processingAst();

      new RelatedCodeMetricAggregator(pico, processor.getSquid(), processor.getRegistry().getLocationList(), processor.getRegistry()).doAggregation();

      // printTestMethodStats(project);

      CodeDocumentGenerator codeDocumentGenerator = CodeDocumentGeneratorFactory.createDefaultInstance(pico, project, getDirectoryToWriteTo(),
          processor.getSquid());
      try
      {
        if (analysisTestFilter != null)
        {
          codeDocumentGenerator.setAnalysisTestFilter(analysisTestFilter);
        }
        codeDocumentGenerator.generate(processor.getRegistry());
      }
      catch (IOException e)
      {
        log.error(e.getMessage() + "", e);
      }
    }
    log.info("finished");
  }

  // private static void printTestMethodStats(final SourceCode code)
  // {
  // if (code.hasChildren())
  // {
  // if ((code.isType(SourceProject.class) || code.isType(SourcePackage.class) || code.isType(SourceFile.class) ||
  // code.isType(SourceClass.class)))
  // {
  // for (SourceCode s : code.getChildren())
  // {
  // printTestMethodStats(s);
  // }
  // }
  // }
  // }

  public MetricAnalyzer filter(final AnalysisTestFilter analysisTestFilter)
  {
    this.analysisTestFilter = analysisTestFilter;
    return this;
  }
}
