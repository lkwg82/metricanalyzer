package de.lgohlke.qdox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.directorywalker.DirectoryScanner;
import com.thoughtworks.qdox.directorywalker.FileVisitor;
import com.thoughtworks.qdox.directorywalker.SuffixFilter;
import com.thoughtworks.qdox.model.JavaSource;
import com.thoughtworks.qdox.parser.ParseException;

import de.lgohlke.AST.Registry;

@Slf4j
@RequiredArgsConstructor
public class JavaDocBuilderFascade extends JavaDocBuilder
{
  private final Registry    registry;
  private static final long serialVersionUID = 9181782830371444316L;

  @Override
  public void addSourceTree(final File file)
  {
    if (log.isDebugEnabled())
    {
      log.debug("scanning tree : " + file);
    }
    super.addSourceTree(file);
  }

  /**
   * Add all files in a directory (and subdirs, recursively). If a file cannot be read, errorHandler will be notified.
   */
  @Override
  public void addSourceTree(final File file, final FileVisitor errorHandler)
  {
    DirectoryScanner scanner = new DirectoryScanner(file);
    scanner.addFilter(new SuffixFilter(".java"));
    scanner.scan(new FileVisitor()
    {
      @Override
      public void visitFile(final File currentFile)
      {
        try
        {
          addSource(currentFile);
        }
        catch (ParseException e)
        {
          // TODO test again with QDOX 2 and try an annotation with \\ in see see
          // http://jira.codehaus.org/browse/QDOX-230
          registry.getQdoxErrorParsedFiles().add(currentFile.getAbsolutePath());
          log.debug("qdox bug with pipes in " + currentFile, e);
        }
        catch (IOException e)
        {
          errorHandler.visitFile(currentFile);
        }
      }
    });
  }

  @Override
  public JavaSource addSource(final File file) throws IOException, FileNotFoundException
  {
    if (log.isDebugEnabled())
    {
      log.debug("scanning file : " + file);
    }

    JavaSource result = null;
    try
    {
      result = super.addSource(file);
    }
    catch (ArrayIndexOutOfBoundsException e)
    {
      // see http://jira.codehaus.org/browse/QDOX-237
      log.error("parser error in qdox: " + e.getMessage() + " in file " + file);
    }
    return result;
  }
}
