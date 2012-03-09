package de.lgohlke.io;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p>JavaSourceFileFinderTest class.</p>
 *
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class JavaSourceFileFinderTest
{
  /**
   * <p>testScanner.</p>
   */
  @Test
  public void testScanner()
  {
    System.setProperty("rootPath", System.getProperty("user.dir"));
    String rootPath = System.getProperty("rootPath");
    final Set<File> files = new TreeSet<File>();

    new JavaSourceFileFinder().addVisitor(new IFileVisitor(){

      @Override
      public void visit(final File file)
      {
        files.add(file);

      }}).scanDirectory(new File(rootPath));

    Assert.assertTrue("must be greater than one", files.size() >= 1);
  }
}
