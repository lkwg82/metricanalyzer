package de.lgohlke.io;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.java.ast.JavaAstScanner;
import org.sonar.java.squid.JavaSquidConfiguration;
import org.sonar.squid.Squid;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceProject;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.Registry;
import de.lgohlke.AST.calculators.DISTANCE_RULE;
import de.lgohlke.AST.visitors.QDoxFileVisitor;
import de.lgohlke.AST.visitors.VariableTypeDistanceVisitor;

public class AstProcessor
{
  private static final Logger log       = LoggerFactory.getLogger(AstProcessor.class);
  private final Squid         squid     = new Squid(new JavaSquidConfiguration(false));
  private final Registry      registry;
  private final Set<File>     scanPaths = new HashSet<File>();

  public AstProcessor()
  {
    registry = new Registry();
  }

  public static class MyJavaAstScanner extends JavaAstScanner
  {

    public MyJavaAstScanner(final JavaSquidConfiguration conf, final SourceCode project)
    {
      super(conf, project);
    }

    @Override
    public JavaAstScanner scanDirectory(final File javaSourceDirectory)
    {
      log.info("scanning " + javaSourceDirectory);
      // do not scan hidden directories/files

      IOFileFilter fileFilter = FileFilterUtils.and(HiddenFileFilter.VISIBLE, FileFilterUtils.fileFileFilter());
      IOFileFilter directoryFilter = FileFilterUtils.and(HiddenFileFilter.VISIBLE, FileFilterUtils.directoryFileFilter());
      List<File> files = new ArrayList<File>(FileUtils.listFiles(javaSourceDirectory, fileFilter, directoryFilter));
      return scanFiles(files);
    }
  }

  public void addDirectories(final File... paths)
  {
    for (File p : paths)
    {
      if (!scanPaths.add(p))
      {
        log.warn("could not add this path: " + p.getAbsolutePath());
      }
    }
  }

  public void init()
  {
    squid.registerVisitor(new QDoxFileVisitor(getRegistry()));
    squid.registerVisitor(new VariableTypeDistanceVisitor(getRegistry(), DISTANCE_RULE.HIGHEST));
  }

  public void scan() throws Exception
  {
    if (scanPaths.size() == 0)
    {
      throw new Exception("scan path list is zero");
    }

    for (File path : scanPaths)
    {
      squid.register(MyJavaAstScanner.class).scanDirectory(path);
    }
  }

  public SourceProject decorateTreeWithMetrics(final MetricDef... metrics)
  {
    log.info("decorating");
    return squid.decorateSourceCodeTreeWith(metrics);
  }

  public Registry getRegistry()
  {
    return registry;
  }

}
