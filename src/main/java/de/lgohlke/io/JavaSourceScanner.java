package de.lgohlke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.picocontainer.MutablePicoContainer;

import de.lgohlke.CommonStore;
import de.lgohlke.AST.Registry;
import de.lgohlke.qdox.QDoxRegistryScanHandler;
import de.lgohlke.qdox.QDoxScanner;
import de.lgohlke.qdox.QDoxTestScanHandler;

/**
 * <p>
 * scans a source tree and collects many information
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
@RequiredArgsConstructor
public class JavaSourceScanner
{
  private final MutablePicoContainer pico;
  private final Registry             registry;
  private Set<String>                paths;

  /**
   * <p>
   * addDirs.
   * </p>
   * 
   * @param files
   *          a {@link java.io.File} object.
   * @throws java.io.IOException
   *           if any.
   */
  public JavaSourceScanner addDirs(final File... files) throws IOException
  {
    SourcePathFixer fixer = new SourcePathFixer(registry);

    log.debug("SourcePathFixer started for :" + files.length);
    for (File f : files)
    {

      if (paths == null)
      {
        paths = fixer.scan(f);
      }
      else
      {
        paths.addAll(fixer.scan(f));
      }
    }
    log.debug("SourcePathFixer finished");

    if (paths.isEmpty())
    {
      throw new IOException("paths fixed to be an empty list ");
    }
    return this;
  }

  /**
   * <p>
   * scan.
   * </p>
   */
  public void scan()
  {
    if (paths == null)
    {
      log.error("please add directories first");
    }
    else
      // if (pico.getComponent(CommonStore.class).getTestClasses().isEmpty())
    {
      log.info("running finder scan()");
      scanSequential();

      // Set<File> files = findFiles();
      // log.debug("files found:" + files.size());
      // scanParallel(files);
    }
  }

  private void scanSequential()
  {
    CommonStore store = pico.getComponent(CommonStore.class);

    // TODO remove registry, test again with QDOX 2 and try an annotation with \\ in see see
    // http://jira.codehaus.org/browse/QDOX-230
    QDoxScanner scanner = new QDoxScanner(registry);

    /**
     * because of scanning x paths and each time the new path is scanned old items will be scanned another time
     */
    QDoxTestScanHandler testScanHandler = new QDoxTestScanHandler();
    scanner.addScanHandler(new EachFileJustOnceFilter(testScanHandler, pico, "tests"));
    scanner.addScanHandler(new EachFileJustOnceFilter(new QDoxRegistryScanHandler(registry), pico, "reg"));

    for (String path : paths)
    {

      File file = new File(path);
      try
      {
        if (log.isDebugEnabled())
        {
          log.debug("scanning " + file);
        }
        scanner.scan(file);
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      // actually a directory
      store.getScannedFiles().add(file);
      store.getTestClasses().addAll(testScanHandler.getTestClasses());
      store.getJavaClasses().addAll(testScanHandler.getClassList());

      testScanHandler.getTestClasses().clear();
      testScanHandler.getClassList().clear();
    }
  }

  // private void scanParallel(final Set<File> files)
  // {
  // log.info("scanning code");
  //
  // QDoxTestScanner testScanHandler = new QDoxTestScanner();
  // QDoxScanner scanner = new QDoxScanner();
  // scanner.addScanHandler(testScanHandler);
  // scanner.addScanHandler(new QDoxRegistryScanHandler(registry));
  //
  // ThreadPool threadPool = new ThreadPool(1);
  //
  // for (File file : files)
  // {
  // threadPool.submit(new ScanJob(pico, file, scanner, testScanHandler));
  // }
  // threadPool.waitForShutdown();
  //
  // }

  @RequiredArgsConstructor
  static class ScanJob implements Runnable
  {
    private final MutablePicoContainer pico;
    private final File                 file;
    // private final Registry registry;
    private final QDoxScanner          scanner;
    private final QDoxTestScanHandler  testScanHandler;

    @Override
    public void run()
    {
      CommonStore store = pico.getComponent(CommonStore.class);

      if (!store.getScannedFiles().contains(file))
      {

        try
        {
          scanner.scan(file);
        }
        catch (FileNotFoundException e)
        {
          log.error(e.getMessage(), e);
        }
        catch (IOException e)
        {
          log.error(e.getMessage(), e);
        }

        store.getScannedFiles().add(file);
        store.getTestClasses().addAll(testScanHandler.getTestClasses());
        store.getJavaClasses().addAll(testScanHandler.getClassList());
      }

    }
  }

  // private Set<File> findFiles()
  // {
  // final Set<File> files = new TreeSet<File>();
  // log.info("parsing directory tree");
  //
  // JavaSourceFileFinder walker = new JavaSourceFileFinder().addVisitor(new IFileVisitor()
  // {
  // @Override
  // public void visit(final File file)
  // {
  // files.add(file);
  // }
  // });
  // for (String path : paths)
  // {
  // walker.scanDirectory(new File(path));
  // }
  // return files;
  // }
}
