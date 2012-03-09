package de.lgohlke.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.DirectoryWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaSourceFileFinder
{
  private static Logger      log        = LoggerFactory.getLogger(JavaSourceFileFinder.class);
  private final List<String> pathList   = new ArrayList<String>();

  private final Set<File>    sourceList = new TreeSet<File>();

  public List<String> getPathList()
  {
    return pathList;
  }

  public void scan()
  {
    JavaDirectoryWalker walker = new JavaDirectoryWalker();

    List<String> pathsToVisit = new ArrayList<String>();
    pathsToVisit.addAll(pathList);

    for (String root : pathsToVisit)
    {
      walker.setRootPath(root);
      getSourceList().addAll(walker.getFiles());
    }

  }

  public Set<File> getSourceList()
  {
    return sourceList;
  }

  private static class JavaDirectoryWalker extends DirectoryWalker<File>
  {
    private String rootPath;

    public List<File> getFiles()
    {
      List<File> files = new ArrayList<File>();

      File directory = new File(rootPath);

      try
      {
        walk(directory, files);
      }
      catch (IOException e)
      {
        log.error("Problem finding configuration files!", e);
      }

      return files;
    }

    public void setRootPath(final String rootPath)
    {
      this.rootPath = rootPath;
    }

    public String getRootPath()
    {
      return rootPath;
    }

    @Override
    protected boolean handleDirectory(final File directory, final int depth, final Collection<File> results) throws IOException
    {
      return !directory.getName().startsWith(".");
    }

    @Override
    protected void handleFile(final File file, final int depth, final Collection<File> results) throws IOException
    {
      if (file.getCanonicalPath().matches(".*\\.java"))
      {
        results.add(file);
      }
    }
  }
}
