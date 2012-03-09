package de.lgohlke.io;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.picocontainer.MutablePicoContainer;
import org.sonar.api.resources.InputFile;
import org.sonar.api.resources.InputFileUtils;
import org.sonar.java.ast.JavaAstScanner;
import org.sonar.java.squid.JavaSquidConfiguration;
import org.sonar.squid.Squid;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceProject;
import org.sonar.squid.measures.MetricDef;

import com.google.common.collect.Lists;

import de.lgohlke.AST.Registry;
import de.lgohlke.AST.calculators.DISTANCE_RULE;
import de.lgohlke.AST.visitors.AssertCountVisitor;
import de.lgohlke.AST.visitors.MethodVisitor;
import de.lgohlke.AST.visitors.QDoxParserErrorsFixerVisitor;
import de.lgohlke.AST.visitors.VariableTypeDistanceVisitor;

/**
 * <p>
 * AstProcessor class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
@Data
public class AstProcessor
{
  private final MutablePicoContainer pico;
  private final Squid                squid     = new Squid(new JavaSquidConfiguration(false));
  private final Registry             registry  = new Registry();
  @Getter(value = AccessLevel.NONE)
  private final Set<File>            scanPaths = new HashSet<File>();

  public static class MyJavaAstScanner extends JavaAstScanner
  {
    public MyJavaAstScanner(final JavaSquidConfiguration conf, final SourceCode project)
    {
      super(conf, project);
    }

    @Override
    public JavaAstScanner scanFiles(final Collection<InputFile> inputFiles)
    {
      JavaAstScanner result = super.scanFiles(inputFiles);
      log.info("finished scanning");
      return result;
    }

    public JavaAstScanner scanDirectories(final Set<File> scanPaths)
    {
      List<InputFile> inputFiles = Lists.newArrayList();
      for (File path : scanPaths)
      {
        Collection<File> files = FileUtils.listFiles(path, FileFilterUtils.fileFileFilter(), FileFilterUtils.directoryFileFilter());
        for (File file : files)
        {
          inputFiles.add(InputFileUtils.create(path, file));
        }
      }
      return scanFiles(inputFiles);
    }
  }

  /**
   * <p>
   * addDirectories.
   * </p>
   * 
   * @param paths
   *          a {@link java.io.File} object.
   * @throws java.io.IOException
   *           if any.
   */
  public void addDirectories(final File... paths) throws IOException
  {
    SourcePathFixer fixer = new SourcePathFixer(registry);
    for (File path : paths)
    {
      for (String _path : fixer.scan(path))
      {
        scanPaths.add(new File(_path));
      }
    }
  }

  /**
   * <p>
   * init.
   * </p>
   */
  public void init()
  {
    // preparation
    squid.registerVisitor(new QDoxParserErrorsFixerVisitor(registry));
    // squid.registerVisitor(new QDoxFileVisitor(registry));
    squid.registerVisitor(new MethodVisitor(registry));

    // real metric visitors
    squid.registerVisitor(new AssertCountVisitor(registry));
    squid.registerVisitor(new VariableTypeDistanceVisitor(registry, DISTANCE_RULE.HIGHEST));
  }

  /**
   * <p>
   * scan.
   * </p>
   * 
   * @throws de.lgohlke.io.AstProcessorException
   *           if any.
   */
  public void scan() throws AstProcessorException
  {

    if (scanPaths.isEmpty())
    {
      throw new AstProcessorException("scan path list is zero");
    }

    try
    {
      new JavaSourceScanner(pico, registry).//
      addDirs(scanPaths.toArray(new File[scanPaths.size()])).//
      scan();
    }
    catch (IOException e)
    {
      throw new AstProcessorException(e);
    }

    //    for (File path : scanPaths)
    //    {
    //      new JavaSourceFileFinder().addVisitor(new IFileVisitor()
    //      {
    //        @Override
    //        public void visit(final File file)
    //        {
    //          squid.register(MyJavaAstScanner.class).scanFile( InputFileUtils.create(basedir, file));
    //        }
    //      }).scanDirectory(path);
    //    }
    squid.register(MyJavaAstScanner.class).scanDirectories(scanPaths);
  }

  /**
   * <p>
   * decorateTreeWithMetrics.
   * </p>
   * 
   * @param metrics
   *          a {@link org.sonar.squid.measures.MetricDef} object.
   * @return a {@link org.sonar.squid.api.SourceProject} object.
   */
  public SourceProject decorateTreeWithMetrics(final MetricDef... metrics)
  {
    log.info("decorating");
    return squid.decorateSourceCodeTreeWith(metrics);
  }
}
