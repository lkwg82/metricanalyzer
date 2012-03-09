package de.lgohlke.syntaxhighlighter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.Squid;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.thoughtworks.qdox.model.JavaMethod;

import de.lgohlke.AnalysisTestFilter;
import de.lgohlke.CommonStore;
import de.lgohlke.AST.ASTMetrics;
import de.lgohlke.AST.Registry;
import de.lgohlke.AST.VisitorKey;
import de.lgohlke.analyzer.MetricsWorthToAnalyze;
import de.lgohlke.analyzer.SourceCodeFinder;
import de.lgohlke.analyzer.sorting.StandardTestOrder;
import de.lgohlke.analyzer.sorting.TestAnalysisOrderer;
import de.lgohlke.concurrent.ThreadPool;
import de.lgohlke.failedTestsfilter.FailedTest;
import de.lgohlke.io.RelatedTestsFinder;
import de.lgohlke.io.TestmethodContext;
import de.lgohlke.io.TestmethodContextFactory;
import de.lgohlke.io.bo.TestClass;
import de.lgohlke.io.bo.TestMethod;
import de.lgohlke.qdox.JavaMethodHashed;

/**
 * creates the code documentation
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
public class CodeDocumentGenerator
{
  private final MutablePicoContainer   pico;

  @Getter
  @Setter
  private File                         resourcePath;
  @Getter
  @Setter
  private File                         targetDirectory;

  private static final String          subDirClasses       = "classes";
  private static final String          subDirMethods       = "testmethods";
  private static final String          subDirMethodsSource = "testmethodsSource";

  private final ThreadPool             writerQueue         = new ThreadPool(1);
  private final ThreadPool             workerPool          = ThreadPool.getInstance();

  private final CommonStore            store;

  private final Squid                  squid;

  @Setter
  private AnalysisTestFilter           analysisTestFilter;

  private HashMap<String, Set<String>> failedTests;
  Map<String, SourceCode> test2code = new HashMap<String, SourceCode>();

  public CodeDocumentGenerator(final MutablePicoContainer pico, final Squid squid)
  {
    this.pico = pico;
    this.squid = squid;

    store = pico.getComponent(CommonStore.class);
  }

  /**
   * <p>
   * generate.
   * </p>
   * 
   * @param registry
   *          a {@link de.lgohlke.AST.Registry} object.
   * @throws Exception
   */
  public void generate(final Registry registry) throws Exception
  {
    if (resourcePath == null)
    {
      throw new NullArgumentException("resourcePath");
    }

    if (targetDirectory == null)
    {
      throw new NullArgumentException("targetDirectory");
    }

    failedTests = new HashMap<String, Set<String>>();
    fillLocalFailedTestsList();
    fillMapTest2Code();
    List<FailedTest> orderedFailedTests = getOrderedFailedTests();

    initTargetDirectory();

    log.info("generating files per class");
    generateSimpleFilePerClass();

    log.info("generating files per testmethod");
    generateFilePerTestMethod(registry);

    generateTestOrderList(orderedFailedTests);

    workerPool.waitForShutdown();

    for (Exception e : workerPool.getExceptions())
    {
      throw e;
    }

    generateOverviewForTests();
    writerQueue.waitForShutdown();
    log.info("complete");
  }

  private void generateTestOrderList(final List<FailedTest> orderedFailedTests)
  {
    final File file = new File(targetDirectory.getAbsolutePath() + "/orderedList.html");

    final TestOverviewTable table = new TestOverviewTable(1);

    for (FailedTest test : orderedFailedTests)
    {
      table.cell(test.getClazz() + "#" + test.getMethod());
    }
    writerQueue.submit(new Runnable()
    {

      @Override
      public void run()
      {
        try
        {
          FileUtils.writeStringToFile(file, table.toString());
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }

      }
    });
  }

  private List<FailedTest> getOrderedFailedTests() throws ConfigurationException, IOException
  {
    List<FailedTest> orderedList =  TestAnalysisOrderer.useMap(test2code).orderWith(new StandardTestOrder());

    return orderedList;
  }

  private void fillLocalFailedTestsList() throws IOException, ConfigurationException
  {
    if ((analysisTestFilter != null) && analysisTestFilter.isActive())
    {
      for (FailedTest test : analysisTestFilter.getFilter().getFailedTests())
      {
        if (!failedTests.containsKey(test.getClazz()))
        {
          failedTests.put(test.getClazz(), new HashSet<String>());
        }
        failedTests.get(test.getClazz()).add(test.getMethod());
      }
    }
  }

  private void generateOverviewForTests()
  {
    final File file = new File(targetDirectory.getAbsolutePath() + "/tests.html");
    final File csv = new File(targetDirectory.getAbsolutePath() + "/tests.csv");
    final StringBuffer b = new StringBuffer();
    final CodeDocument document = new CodeDocument().baseDirectory(".").title("Overview");

    // for (int i = 0; i < 15; i++)
    // {
    // document.text("<div><p>&nbsp;</p></div>");
    // }

    HtmlTable table = new TestOverviewTable(1 + MetricsWorthToAnalyze.LIST.size());
    {
      table.setStyle("width:40% !important;");
      // table.setColumnStyle("<col style=\"width:70%\"/><col span=\"15\" style=\"width:15px\"/>");
    }
    // header
    table.cell("test");
    b.append("test");
    for (MetricDef m : MetricsWorthToAnalyze.LIST)
    {
      // String[] splitted = m.getName().split("");
      // String joined = StringUtils.join(splitted, "</br>");

      table.cell("<div><span>" + m.getName() + "</span></div>");
      b.append("|" + m.getName());
    }
    b.append('\n');



    for (Entry<String, SourceCode> entry : test2code.entrySet())
    {
      if (entry.getValue() == null)
      {
        log.error("code was null for " + entry.getKey());
      }
      else
      {
        // alway <class>#<method>
        String test = entry.getKey().split("#")[1];

        String link = test;
        link = link.replaceFirst("\\(.*", "");
        link = link.replaceAll(".*\\ ", "");
        String linkText = StringUtils.reverse(StringUtils.reverse(link).replaceFirst("\\.", "#"));
        table.cell("<a href=\"testmethods/" + linkText.replace("#", "%23") + "().html\">" + linkText + "</a>");
        b.append(test);
        for (MetricDef metric : MetricsWorthToAnalyze.LIST)
        {
          String metricFormatted = new MetricFormatter(metric, entry.getValue()).toString();
          table.cell(metricFormatted);
          b.append("|" + metricFormatted);
        }
      }
      b.append('\n');
    }

    document.text("<div id=\"metricHint\" name=\"" + subDirMethodsSource + "\"></div>");
    document.text("<div id=\"inlinesource\"></div>");
    document.text(table.toString());

    writerQueue.submit(new RunnableDocumentWriter(document, file));
    writerQueue.submit(new Runnable()
    {

      @Override
      public void run()
      {
        try
        {
          FileUtils.writeStringToFile(csv, b.toString());
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }

      }
    });
  }
  /**
   * 
   */
  private void fillMapTest2Code()
  {
    for (TestClass clazz : store.getTestClasses())
    {
      String _clazz = clazz.getClazz().getFullyQualifiedName();
      if (failedTests.isEmpty() || failedTests.containsKey(_clazz))
      {
        if (log.isDebugEnabled())
        {
          log.debug("generating for TestClass " + clazz);
        }

        for (TestMethod test : clazz.getTests())
        {
          String method = test.getMethod().getName();
          if (failedTests.isEmpty() || failedTests.get(_clazz).contains(method))
          {
            SourceCode code = new SourceCodeFinder(squid).findSourceCodeFor(test.getMethod());

            String key = _clazz + "#" + method;
            test2code.put(key, code);
          }
        }
      }
    }
  }

  private void generateFilePerTestMethod(final Registry registry) throws IOException
  {
    String baseDir = targetDirectory.getAbsolutePath() + "/" + subDirMethods;
    String baseDirSource = targetDirectory.getAbsolutePath() + "/" + subDirMethodsSource;

    for (TestClass clazz : store.getTestClasses())
    {
      String _clazz = clazz.getClazz().getFullyQualifiedName();
      if (failedTests.isEmpty() || failedTests.containsKey(_clazz))
      {
        if (log.isDebugEnabled())
        {
          log.debug("generating for TestClass " + clazz);
        }

        for (TestMethod test : clazz.getTests())
        {
          String method = test.getMethod().getName();
          if (failedTests.isEmpty() || failedTests.get(_clazz).contains(method))
          {
            log.info("generating docs for " + test);
            // documents with detailed information
            workerPool.submit(createDocumentJob(new DocumentJobconfiguration(clazz, test, registry, baseDir, pico)));
            // documents for the overlay
            workerPool.submit(createSimpleDocumentJob(new DocumentJobconfiguration(clazz, test, registry, baseDirSource, pico)));
          }
        }
      }
    }
  }

  @Data
  @RequiredArgsConstructor
  class DocumentJobconfiguration
  {
    private final TestClass            clazz;
    private final TestMethod           test;
    private final Registry             registry;
    private final String               baseDir;
    private final MutablePicoContainer pico;
  }

  private Runnable createSimpleDocumentJob(final DocumentJobconfiguration c)
  {
    return new Runnable()
    {

      @Override
      public void run()
      {

        if (log.isDebugEnabled())
        {
          log.debug("generating simplecode for " + c.test);
        }

        String title = c.clazz.getClazz().getFullyQualifiedName();
        String tempTitle = title + "#" + c.test.getMethod().getCallSignature();
        final File html = new File(c.baseDir + "/" + tempTitle + ".html");

        RelatedTestsFinder relatedTestfinder = pico.getComponent(RelatedTestsFinder.class);
        TestmethodContext queryResult = TestmethodContextFactory.createByTest(relatedTestfinder, c.test);

        final CodeDocument document = new CodeDocument();

        for (JavaMethodHashed method : queryResult.getRelatedMethods())
        {
          document.text(new CodeSnippet(method.getMethod()).toString());
        }

        writerQueue.submit(new Runnable()
        {
          @Override
          public void run()
          {
            try
            {
              document.text(new CodeSnippet(c.test).toString());
              document.baseDirectory("..");
              document.writeToFile(html);
            }
            catch (IOException e)
            {
              log.error(e.getMessage(), e);
            }
          }
        });

      }
    };

  }

  @RequiredArgsConstructor
  static class DocumentJob implements Runnable
  {
    private final DocumentJobconfiguration c;
    private final CodeDocumentHelper       helper = new CodeDocumentHelper();
    private final Squid                    squid;
    private final ThreadPool               writerQueue;

    private Set<MetricDef> getSetOfMetrics(final Map<VisitorKey, Map<MetricDef, Double>> measures)
    {
      Set<MetricDef> metrics = new TreeSet<MetricDef>();

      for (Map<MetricDef, Double> entry : measures.values())
      {
        for (MetricDef m : entry.keySet())
        {
          metrics.add(m);
        }
      }
      return metrics;
    }

    private HtmlDivTable generateLink2HintTable(final JavaMethod method, final Registry registry)
    {
      int rows = method.getSourceCode().split("\\r?\\n").length;
      HtmlDivTable table = new HtmlDivTable(1, rows);

      Map<VisitorKey, Map<MetricDef, Double>> measures = getMeasuresForThisTest(method, registry);

      for (int line = -1; line < (rows - 1); line++)
      {
        boolean found = false;
        int actualLine = line + method.getLineNumber() + 1;
        for (Entry<VisitorKey, Map<MetricDef, Double>> entry : measures.entrySet())
        {
          if (entry.getKey().getStartLine() == actualLine)
          {
            found = true;
          }
        }

        if (found)
        {
          String link = String.format("<a href=\"#hint%d\">info</a>", line + 1);
          table.cell(link);
        }
        else
        {
          table.cell("&nbsp;");
        }
      }

      return table;
    }

    private HtmlDivTable generateMethodStatTable(final JavaMethod method)
    {
      int rows = ASTMetrics.values().length + Metric.values().length;
      HtmlDivTable table = new HtmlDivTable(2, rows);
      {
        table.setStyle("width:30% !important;");
        table.setColumnStyle("<col style=\"width:70%\"/><col style=\"width:30%\"/>");
      }

      SourceCode code = new SourceCodeFinder(squid).findSourceCodeFor(method);

      if (code == null)
      {
        log.error("could not find code for " + method);
        // new ProjectPrinter(project).print();
      }
      else
      {

        table.cell("Metrik");
        table.cell("Value");

        for (MetricDef metric : MetricsWorthToAnalyze.LIST)
        {
          table.cell(metric.toString());
          table.cell(String.valueOf(code.getDouble(metric)));
        }
      }

      return table;
    }

    private String generateHint(final TestMethod test, final Registry registry, final int line)
    {
      StringBuffer b = new StringBuffer();

      Map<VisitorKey, Map<MetricDef, Double>> measures = getMeasuresForThisTest(test.getMethod(), registry);

      int actualLine = line + test.getMethod().getLineNumber();
      for (Entry<VisitorKey, Map<MetricDef, Double>> entry : measures.entrySet())
      {
        if (entry.getKey().getStartLine() == actualLine)
        {
          b.append("<div class=\"hint\">");
          b.append(String.format("<a name=\"hint%d\"></a>", line));
          b.append("<b>Line:</b> ");
          b.append(actualLine);
          b.append("<b>Token:</b> ");
          b.append(entry.getKey().getAstTokenString());
          b.append("<b>identifier:</b> ");
          b.append(entry.getKey().getIdentifier());
          b.append("<br/>");
          for (Entry<MetricDef, Double> measure : entry.getValue().entrySet())
          {
            b.append("<div class=\"metric\">");
            b.append("metric: ");
            b.append(measure.getKey());
            b.append(" &nbsp; &nbsp; ");
            b.append("value: ");
            b.append(measure.getValue());
            b.append("<br/>");

            int showLinesAround = 2;
            String code = helper.getFormattedCode(test, actualLine, showLinesAround, actualLine - showLinesAround);
            b.append(code);

            if (registry.getDescription().containsKey(entry.getKey()) && registry.getDescription().get(entry.getKey()).containsKey(measure.getKey()))
            {
              b.append("<div class=\"description\">");
              b.append("<fieldset>");
              b.append("<legend>calculator description</legend>");
              b.append(registry.getDescription().get(entry.getKey()).get(measure.getKey()));
              b.append("</fieldset>");
              b.append("</div>");
            }

            b.append("</div>");
          }
          b.append("</div>");
        }
      }

      return b.toString();
    }

    private Map<VisitorKey, Map<MetricDef, Double>> getMeasuresForThisTest(final JavaMethod test, final Registry registry)
    {
      String file = test.getSource().getURL().getFile();
      String testname = test.getName();

      Map<VisitorKey, Map<MetricDef, Double>> result = new HashMap<VisitorKey, Map<MetricDef, Double>>();
      int firstLine = test.getLineNumber();
      int lastLine = 0;

      List<VisitorKey> metricsInThisFile = new ArrayList<VisitorKey>();

      for (Entry<VisitorKey, String[]> entry : registry.getCode().entrySet())
      {
        VisitorKey key = entry.getKey();
        if (key.getFilename().equals(file))
        {
          if ((key.getAstToken() == TokenTypes.METHOD_DEF) && key.getIdentifier().equals(testname) && (key.getStartLine() == firstLine))
          {
            lastLine = key.getEndLine();
          }

          metricsInThisFile.add(key);
        }
      }

      // filter only metrics according to this test
      for (VisitorKey key : metricsInThisFile)
      {
        if ((key.getStartLine() >= firstLine) && (key.getEndLine() <= lastLine) && registry.getMetricPerLineMap().containsKey(key))
        {
          Map<MetricDef, Double> value = registry.getMetricPerLineMap().get(key);
          result.put(key, value);
        }
      }

      return result;
    }

    private HtmlDivTable generateMetricTable(final JavaMethod method, final Registry registry)
    {

      int linesOfCode = method.getSourceCode().split("\\r?\\n").length + 2;
      Map<VisitorKey, Map<MetricDef, Double>> measures = getMeasuresForThisTest(method, registry);
      Set<MetricDef> metrics = getSetOfMetrics(measures);

      HtmlDivTable metricTable = new HtmlDivTable(metrics.size(), 1 + linesOfCode);

      fillMetricTable(metricTable, metrics, measures, linesOfCode, method.getLineNumber());

      return metricTable;
    }

    private void fillMetricTable(final HtmlDivTable metricTable, final Set<MetricDef> metrics, final Map<VisitorKey, Map<MetricDef, Double>> measures,
        final int linesOfCode, final int startline)
    {
      MetricDef[] metricArray = metrics.toArray(new MetricDef[metrics.size()]);
      Double[] values = new Double[metricArray.length * linesOfCode];

      for (int i = 0; i < values.length; i++)
      {
        values[i] = 0D;
      }

      for (int i = 0; i < values.length; i++)
      {
        int col = i % metricArray.length;
        int row = (int) Math.floor(i / (double) metricArray.length);

        MetricDef currentMetric = metricArray[col];

        int codeLine = (row + startline + 1);
        for (Entry<VisitorKey, Map<MetricDef, Double>> entry : measures.entrySet())
        {
          VisitorKey key = entry.getKey();
          if ((key.getStartLine() + 1) == codeLine)
          {
            Double v = entry.getValue().get(currentMetric);
            if (v != null)
            {
              values[i] += v;
            }
          }
        }

        metricTable.cell(values[i].toString());
      }

      for (MetricDef m : metricArray)
      {
        metricTable.cell(((ASTMetrics) m).getShortName());
      }

    }

    @Override
    public void run()
    {

      if (log.isDebugEnabled())
      {
        log.debug("generating for " + c.test);
      }

      String title = c.clazz.getClazz().getFullyQualifiedName();
      String tempTitle = title + "#" + c.test.getMethod().getCallSignature();
      final File html = new File(c.baseDir + "/" + tempTitle + ".html");

      List<JavaMethod> tests = new ArrayList<JavaMethod>();
      tests.add(c.test.getMethod());
      { // adding related tests
        RelatedTestsFinder relatedTestfinder = c.pico.getComponent(RelatedTestsFinder.class);
        TestmethodContext queryResult = TestmethodContextFactory.createByTest(relatedTestfinder, c.test);

        for (JavaMethodHashed method : queryResult.getRelatedMethods())
        {
          tests.add(method.getMethod());
        }
      }
      CodeDocument document = new CodeDocument().//
          title(tempTitle).//
          baseDirectory("../");

      boolean displayedForTest = false;
      for (JavaMethod method : tests)
      {
        HtmlDivTable metricTable = generateMetricTable(method, c.registry);
        HtmlDivTable link2HintTable = generateLink2HintTable(method, c.registry);

        CodeSnippet codeSnippet = new CodeSnippet(method);
        DivTable divTable = new DivTable().//
            width(60, 300, 1800).//
            cell(link2HintTable.toString()).//
            cell(metricTable.toString() + "\n").//
            cell(codeSnippet.toString());

        document.text(divTable.toString());

        if (!displayedForTest)
        {
          displayedForTest = true;
          HtmlDivTable methodStatTable = generateMethodStatTable(method);
          document.text(methodStatTable.toString());
        }

      }

      int lines = c.test.getMethod().getSourceCode().split("\\r?\\n").length;
      for (int i = 0; i < lines; i++)
      {
        String linkTable = generateHint(c.test, c.registry, i);
        document = document.text(linkTable + "\n");
      }

      writerQueue.submit(new RunnableDocumentWriter(document, html));
    }
  }

  @RequiredArgsConstructor
  private static class RunnableDocumentWriter implements Runnable
  {
    private final CodeDocument document;
    private final File         file;

    @Override
    public void run()
    {
      try
      {
        document.writeToFile(file);
      }
      catch (IOException e)
      {
        log.error(e.getMessage(), e);
      }

    }

  }

  private Runnable createDocumentJob(final DocumentJobconfiguration c)
  {
    return new DocumentJob(c, squid, writerQueue);
  }

  private void generateSimpleFilePerClass() throws IOException, ConfigurationException
  {
    String baseDir = targetDirectory.getAbsolutePath() + "/" + subDirClasses;
    for (TestClass clazz : store.getTestClasses())
    {
      if (failedTests.isEmpty() || failedTests.containsKey(clazz.getClazz().getFullyQualifiedName()))
      {
        String title = clazz.getClazz().getFullyQualifiedName();
        final File html = new File(baseDir + "/" + title + ".html");

        CodeSnippet codeSnippet = new CodeSnippet(clazz);
        DivTable divTable = new DivTable().//
            width(1300, 200).//
            cell(codeSnippet.toString()).//
            cell("test");

        final CodeDocument document = new CodeDocument().//
            title(title).//
            baseDirectory("../").//
            text(divTable.toString());//

        writerQueue.submit(new Runnable()
        {
          @Override
          public void run()
          {
            try
            {
              document.writeToFile(html);
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
          }
        });
      }
    }
  }

  private void initTargetDirectory() throws IOException
  {
    if (targetDirectory.exists())
    {
      FileUtils.deleteDirectory(targetDirectory);
    }

    if (!targetDirectory.mkdirs())
    {
      throw new IOException("mkdirs of " + targetDirectory.getAbsolutePath() + "  failed for some reason");
    }

    File scriptSourceDir = new File(resourcePath.getAbsolutePath() + "/scripts");
    File scriptDir = new File(targetDirectory.getAbsolutePath() + "/scripts");

    File cssSourceDir = new File(resourcePath.getAbsolutePath() + "/styles");
    File cssDir = new File(targetDirectory.getAbsolutePath() + "/styles");

    FileUtils.copyDirectory(scriptSourceDir, scriptDir);
    FileUtils.copyDirectory(cssSourceDir, cssDir);

    for (File myFile : FileUtils.listFiles(new File("src/main/resources"), new String[] { "css", "js" }, false))
    {
      FileUtils.copyFile(myFile, new File(targetDirectory.getAbsolutePath() + "/" + myFile.getName()));
    }
  }
}
