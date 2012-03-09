package de.lgohlke.qdox;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;

import de.lgohlke.AST.Registry;

public class QDoxContext
{
  private final JavaDocBuilder builder = new JavaDocBuilder();
  private final Logger         log     = LoggerFactory.getLogger(this.getClass());
  private final Registry       registry;

  public QDoxContext()
  {
    registry = new Registry();
    builder.addSourceTree(new File("."));
  }

  public QDoxContext(final Registry registry)
  {
    this.registry = registry;
  }

  public JavaClass getClassByName(final String name)
  {
    return builder.getClassByName(name);
  }

  public void parse(final String filename) throws IOException
  {
    File path = new File(filename);

    File fullpath;
    if (path.isDirectory())
    {
      fullpath = path;
    }
    else
    {
      fullpath = path.getParentFile();
    }

    if (registry.getIndexedPaths().add(fullpath.getAbsolutePath()))
    {
      log.debug("starting parsing:" + fullpath);
      builder.addSourceTree(fullpath);

      for (JavaSource source : builder.getSources())
      {
        String file = source.getURL().getFile();
        registry.getIndexedFiles().add(file);

        registry.getPackageName().put(file, source.getPackage() == null ? "<default>" : source.getPackage().getName());
        registry.setImports(file, source.getImports());
      }
      log.debug("finished parsing");
    }
  }
}
