package de.lgohlke.syntaxhighlighter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceProject;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import de.lgohlke.AST.ASTMetrics;
import de.lgohlke.AST.Registry;
import de.lgohlke.AST.visitors.VisitorKey;
import de.lgohlke.analyzer.MetricsWorthToAnalyze;
import de.lgohlke.analyzer.SourceCodeFinder;
import de.lgohlke.concurrent.ThreadPool;
import de.lgohlke.io.JavaTestClassFinder;
import de.lgohlke.io.bo.TestClass;
import de.lgohlke.io.bo.TestMethod;

/**
 * creates the code documentation
 * 
 * @author lars
 */
public class CodeDocumentGenerator
{
  private final static Logger       log           = LoggerFactory.getLogger(CodeDocumentGenerator.class);

  private File                      resourcePath;
  private File                      targetDirectory;
  private final JavaTestClassFinder finder;

  private static final String       subDirClasses = "classes";
  private static final String       subDirMethods = "testmethods";

  private final ThreadPool          writerQueue   = new ThreadPool(1);
  private final ThreadPool          workerPool    = ThreadPool.getInstance();
  private final CodeDocumentHelper  helper        = new CodeDocumentHelper();

  private final SourceProject       project;

  public CodeDocumentGenerator(final JavaTestClassFinder finder, final SourceProject project)
  {
    this.finder = finder;
    this.project = project;
  }

  public void setResourcePath(final File resourcePath)
  {
    this.resourcePath = resourcePath;
  }

  public File getResourcePath()
  {
    return resourcePath;
  }

  public void setTargetDirectory(final File targetDirectory)
  {
    this.targetDirectory = targetDirectory;
  }

  public File getTargetDirectory()
  {
    return targetDirectory;
  }

  public void generate(final Registry registry) throws IOException
  {
    if (resourcePath == null)
    {
      throw new NullArgumentException("resourcePath");
    }

    if (targetDirectory == null)
    {
      throw new NullArgumentException("targetDirectory");
    }

    initTargetDirectory();

    if (finder.getTestClasses().size() == 0)
    {
      log.info("running finder scan()");
      finder.scan();
    }

    log.info("generating files per class");
    generateSimpleFilePerClass();

    log.info("generating files per testmethod");
    generateSimpleFilePerTestMethod(registry);

    workerPool.waitForShutdown();

    generateOverviewForTests();
    writerQueue.waitForShutdown();
    log.info("complete");
  }

  private void generateOverviewForTests()
  {
    final File file = new File(targetDirectory.getAbsolutePath() + "/tests.html");
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
    for (MetricDef m : MetricsWorthToAnalyze.LIST)
    {
      // String[] splitted = m.getName().split("");
      // String joined = StringUtils.join(splitted, "</br>");

      table.cell("<div><span>" + m.getName() + "</span></div>");
    }

    for (TestClass clazz : finder.getTestClasses())
    {
      if (log.isDebugEnabled())
      {
        log.debug("generating for TestClass " + clazz);
      }

      for (TestMethod test : clazz.getTests())
      {
        SourceCode code = new SourceCodeFinder(project).findSourceCodeFor(test);

        String link = test.getMethod().toString();
        link = link.replaceFirst("\\(.*", "");
        link = link.replaceAll(".*\\ ", "");
        String linkText = StringUtils.reverse(StringUtils.reverse(link).replaceFirst("\\.", "#"));
        table.cell("<a href=\"testmethods/" + linkText.replace("#", "%23") + "().html\">" + linkText + "</a>");
        for (MetricDef metric : MetricsWorthToAnalyze.LIST)
        {
          table.cell(String.valueOf(code.getDouble(metric)));
        }
      }
    }

    document.text(table.toString());

    writerQueue.submit(new Runnable()
    {
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
    });
  }

  private void generateSimpleFilePerTestMethod(final Registry registry) throws IOException
  {
    String baseDir = targetDirectory.getAbsolutePath() + "/" + subDirMethods;

    for (TestClass clazz : finder.getTestClasses())
    {
      if (log.isDebugEnabled())
      {
        log.debug("generating for TestClass " + clazz);
      }

      for (TestMethod test : clazz.getTests())
      {
        workerPool.submit(createDocumentJob(clazz, test, registry, baseDir));
      }
    }
  }

  private Runnable createDocumentJob(final TestClass clazz, final TestMethod test, final Registry registry, final String baseDir)
  {
    return new Runnable()
    {

      public void run()
      {

        if (log.isDebugEnabled())
        {
          log.debug("generating for " + test);
        }

        String title = clazz.getClazz().getFullyQualifiedName();
        String tempTitle = title + "#" + test.getMethod().getCallSignature();
        final File html = new File(baseDir + "/" + tempTitle + ".html");

        HtmlDivTable metricTable = generateMetricTable(test, registry);
        // HtmlTable linkTable= generateLinkTable();

        HtmlDivTable link2HintTable = generateLink2HintTable(test, registry);
        HtmlDivTable methodStatTable = generateMethodStatTable(test);

        CodeSnippet codeSnippet = new CodeSnippet(test);
        DivTable divTable = new DivTable().//
            width(45, 200, 1800).//
            cell(link2HintTable.toString()).//
            cell(metricTable.toString() + "\n").//
            cell(codeSnippet.toString());

        CodeDocument document = new CodeDocument().//
            title(tempTitle).//
            baseDirectory("../").//
            text(divTable.toString()).//
            text(methodStatTable.toString());

        int lines = test.getMethod().getSourceCode().split("\\r?\\n").length;
        for (int i = 0; i < lines; i++)
        {
          String linkTable = generateHint(test, registry, i);
          document = document.text(linkTable + "\n");
        }

        final CodeDocument documentForWriting = new CodeDocument(document);

        writerQueue.submit(new Runnable()
        {
          public void run()
          {
            try
            {
              documentForWriting.writeToFile(html);
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

  private HtmlDivTable generateMethodStatTable(final TestMethod test)
  {
    int rows = ASTMetrics.values().length + Metric.values().length;
    HtmlDivTable table = new HtmlDivTable(2, rows);
    {
      table.setStyle("width:30% !important;");
      table.setColumnStyle("<col style=\"width:70%\"/><col style=\"width:30%\"/>");
    }

    SourceCode code = new SourceCodeFinder(project).findSourceCodeFor(test);

    if (code == null)
    {
      log.error("could not find code for " + test);
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

  private HtmlDivTable generateLink2HintTable(final TestMethod test, final Registry registry)
  {
    int rows = test.getMethod().getSourceCode().split("\\r?\\n").length;
    HtmlDivTable table = new HtmlDivTable(1, rows);

    Map<VisitorKey, Map<MetricDef, Double>> measures = getMeasuresForThisTest(test, registry);

    for (int line = 0; line < rows; line++)
    {
      boolean found = false;
      int actualLine = line + test.getMethod().getLineNumber() + 1;
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

  private String generateHint(final TestMethod test, final Registry registry, final int line)
  {
    StringBuffer b = new StringBuffer();

    Map<VisitorKey, Map<MetricDef, Double>> measures = getMeasuresForThisTest(test, registry);

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

  private HtmlDivTable generateMetricTable(final TestMethod test, final Registry registry)
  {

    int linesOfCode = test.getMethod().getSourceCode().split("\\r?\\n").length + 2;
    Map<VisitorKey, Map<MetricDef, Double>> measures = getMeasuresForThisTest(test, registry);
    Set<MetricDef> metrics = getSetOfMetrics(measures);

    HtmlDivTable metricTable = new HtmlDivTable(metrics.size(), 1 + linesOfCode);

    fillMetricTable(metricTable, metrics, measures, linesOfCode, test.getMethod().getLineNumber());

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
        if (key.getStartLine() == codeLine)
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

  private Map<VisitorKey, Map<MetricDef, Double>> getMeasuresForThisTest(final TestMethod test, final Registry registry)
  {
    String file = test.getMethod().getSource().getURL().getFile();
    String testname = test.getMethod().getName();

    Map<VisitorKey, Map<MetricDef, Double>> result = new HashMap<VisitorKey, Map<MetricDef, Double>>();
    int firstLine = test.getMethod().getLineNumber();
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

  private void generateSimpleFilePerClass() throws IOException
  {
    String baseDir = targetDirectory.getAbsolutePath() + "/" + subDirClasses;
    for (TestClass clazz : finder.getTestClasses())
    {
      String title = clazz.getClazz().getFullyQualifiedName();
      File html = new File(baseDir + "/" + title + ".html");

      CodeSnippet codeSnippet = new CodeSnippet(clazz);
      DivTable divTable = new DivTable().//
          width(1300, 200).//
          cell(codeSnippet.toString()).//
          cell("test");

      new CodeDocument().//
          title(title).//
          baseDirectory("../").//
          text(divTable.toString()).//
          writeToFile(html);
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

    for (File myFile : FileUtils.listFiles(new File("src/main/resources"), new String[]
    { "css", "js" }, false))
    {
      FileUtils.copyFile(myFile, new File(targetDirectory.getAbsolutePath() + "/" + myFile.getName()));
    }
  }
}
