package de.lgohlke.qdox;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;

/**
 * <p>QdoxBug class.</p>
 *
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class QdoxBug
{
  /**
   * <p>testBug.</p>
   *
   * @throws java.io.IOException if any.
   */
  @Test
  public void testBug() throws IOException
  {
    File file = new File("src/test/java/de/lgohlke/qdox/QdoxBug.java");

    JavaSource source = new JavaDocBuilder().addSource(file);

    for (JavaClass clazz : source.getClasses())
    {
      System.out.println(clazz.getSource().toString());
    }
  }

  @Test(expected=ArrayIndexOutOfBoundsException.class)
  public void testQDoxParserBug() throws IOException{
    File file = new File("src/test/resources/de/lgohlke/test/LocalizedFormats.java");
    new JavaDocBuilder().addSource(file);
  }
}
