package de.lgohlke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.thoughtworks.qdox.model.JavaSource;
import com.thoughtworks.qdox.parser.ParseException;

import de.lgohlke.AST.Registry;
import de.lgohlke.qdox.IQDoxScanHandler;
import de.lgohlke.qdox.QDoxScanner;

/**
 * sonar now requires paths to be given, that are the correct basedirs according to package-declaration in the java
 * files found in these directory hierarchies e.g.
 * <p/>
 * so <b>src/</b> would be splitted into
 * <ul>
 * <li>src/main/java</li>
 * <li>src/test/java</li>
 * </ul>
 * according to the files in the hierarchies below
 * 
 * @since sonar 2.8
 * @author lars
 * @version $Id: $
 */
@Slf4j
@RequiredArgsConstructor
public class SourcePathFixer
{
  private final Registry registry;

  public Set<String> scan(final File baseDirectory) throws IOException
  {
    if (baseDirectory.exists())
    {
      if (baseDirectory.isDirectory())
      {
        log.info("fixing : " + baseDirectory);

        final PackageFindHandler packageFindHandler = new PackageFindHandler();
        final QDoxScanner scanner = new QDoxScanner(registry).addScanHandler(packageFindHandler);

        final Set<String> paths = new TreeSet<String>();
        // final Set<File> files = new TreeSet<File>();

        final Set<String> results = new TreeSet<String>();

        IFileVisitor visitor = new IFileVisitor()
        {
          @Override
          public void visit(final File file)
          {
            String directory = file.getParentFile().getAbsolutePath();
            // only one file per directory-hierarchy
            if (paths.add(directory))
            {
              try
              {
                scanner.scan(file);
                if (packageFindHandler.getPath() != null)
                {
                  results.add(packageFindHandler.getPath());
                }
              }
              catch (ParseException e)
              {
                // TODO test again with QDOX 2 and try an annotation with \\ in see see
                // http://jira.codehaus.org/browse/QDOX-230
                log.debug("qdox has an bug with pipes, we ignore this file and use next", e);
                paths.remove(directory);
              }
              catch (IOException e)
              {
                log.error(e.getMessage(), e);
                paths.remove(directory);
              }
            }
          }
        };

        new JavaSourceFileFinder().addVisitor(visitor).scanDirectory(baseDirectory);

        // clean
        return results; // cleanupPaths(results);
      }
      else
      {
        throw new NotADirectoryException(baseDirectory + " is not a directory");
      }
    }
    else
    {
      throw new FileNotFoundException(baseDirectory + " was not found");
    }
  }

  // private Set<String> cleanupPaths(final Set<String> paths)
  // {
  // Set<String> results = new TreeSet<String>(paths);
  // for (String path : paths)
  // {
  // if (path.endsWith("/target/test-classes") || path.endsWith("/test/resources"))
  // {
  // results.remove(path);
  // }
  // }
  // return paths;
  // }

  class PackageFindHandler implements IQDoxScanHandler
  {
    @Getter
    private String path;

    @Override
    public void scanSource(final JavaSource source)
    {
      if (source.getPackage() != null)
      {

        String file = source.getURL().getFile();
        String ppackage = source.getPackage().getName();
        String packagePath = ppackage.replaceAll("\\.", "/");

        // System.out.println(file);
        // System.out.println(packagePath);

        int index = file.lastIndexOf(packagePath);
        if (index == -1)
        {
          if (file.contains("src/site"))
          {
            log.info("error of files in src/site is common, no worry : " + file);
          }
          else
          {
            log.error("not found :" + packagePath + " - " + file);
          }
        }
        else
        {
          path = file.substring(0, index - 1);
        }
      }
    }
  }

}
