package de.lgohlke.io;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import de.lgohlke.AST.Registry;

/**
 * <p>
 * SourcePathFixerTest class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class SourcePathFixerTest
{

  /**
   * <p>
   * testHandle.
   * </p>
   * 
   * @throws java.io.IOException
   *           if any.
   */
  @Test
  public void testHandle() throws IOException
  {
    SourcePathFixer fixer = new SourcePathFixer(new Registry());
    File path = new File(System.getProperty("user.dir"));

    Set<String> paths = fixer.scan(path);
    Assert.assertEquals("should be 4 src/main/java, src/test/java and src/test/resources", 4, paths.size());
    Assert.assertTrue("should contain src/main/java", paths.contains(new File("src").getAbsolutePath() + "/main/java"));
  }

}
