package de.lgohlke.analyzer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sonar.api.resources.InputFile;
import org.sonar.api.resources.InputFileUtils;
import org.sonar.java.ast.JavaAstScanner;
import org.sonar.java.squid.JavaSquidConfiguration;
import org.sonar.squid.Squid;
import org.sonar.squid.api.SourceClass;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceFile;
import org.sonar.squid.api.SourceMethod;
import org.sonar.squid.api.SourcePackage;
import org.sonar.squid.api.SourceProject;

import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaSource;
import com.thoughtworks.qdox.model.Type;

/**
 * <p>
 * SourceCodeFinderTest class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class SourceCodeFinderTest
{
  private static JavaMethod       myJavaMethod;
  private static JavaMethod       myWrongJavaMethod;
  private static Squid            squid  = new Squid(new JavaSquidConfiguration());
  private static SourceCodeFinder finder = new SourceCodeFinder(squid);

  // private final MutableObject status = new MutableObject();
  //
  // public static class MutableObject
  // {
  // private boolean status;
  //
  // public void setStatus(final boolean status)
  // {
  // this.status = status;
  // }
  //
  // public boolean isStatus()
  // {
  // return status;
  // }
  // }
  //
  /**
   * <p>
   * setup.
   * </p>
   * 
   * @throws MalformedURLException
   */
  @BeforeClass
  public static void setup() throws MalformedURLException
  {
    SourceProject project = new SourceProject("myProject");
    SourcePackage myPackage = new SourcePackage("org.package");
    SourceFile myFile = new SourceFile("/tmp/xyz");
    SourceClass myClass = new SourceClass(myPackage + ".clazz");
    myJavaMethod = new JavaMethod(Type.VOID, myClass.toString() + ".test");
    {
      myJavaMethod.setModifiers(new String[] { "public" });
      JavaClass clazz = new JavaClass(new Random().nextLong() + "");
      {
        JavaSource source = new JavaSource();
        source.setURL(new URL("file://" + System.getProperty("user.dir") + "/" + "de/lgohlke/test/DummyClass.java"));
        clazz.setSource(source);
      }
      myJavaMethod.setParentClass(clazz);
    }
    myJavaMethod.setLineNumber(7);

    myWrongJavaMethod = new JavaMethod(Type.VOID, myClass.toString() + ".testWrong");
    myWrongJavaMethod.setModifiers(new String[] { "public" });
    myWrongJavaMethod.setParentClass(myJavaMethod.getParentClass());

    // String methodKey = myJavaMethod.getDeclarationSignature(true);
    SourceMethod myMethod = mock(SourceMethod.class);

    when(myMethod.getName()).thenReturn("test()");
    when(myMethod.getStartAtLine()).thenReturn(myJavaMethod.getLineNumber());
    when(myMethod.isType(SourceMethod.class)).thenReturn(true);
    when(myMethod.getParent()).thenReturn(myClass);

    project.addChild(myPackage);
    myPackage.addChild(myFile);
    myFile.addChild(myClass);
    myClass.addChild(myMethod);

    squid.register(JavaAstScanner.class).scanFile(createInputFile("de/lgohlke/test/DummyClass.java"));
  }

  private static InputFile createInputFile(final String relativeFile)
  {
    String baseDir = "src/test/resources";
    return InputFileUtils.create(new File(baseDir), new File(baseDir + "/" + relativeFile));
  }

  @Test
  public void testFindSourceCodeFor()
  {
    SourceCode code = finder.findSourceCodeFor(myJavaMethod);

    Assert.assertNotNull(code);
  }

  @Test
  public void testFindSourceCodeForNot()
  {
    SourceCode code = finder.findSourceCodeFor(myWrongJavaMethod);

    Assert.assertNull(code);
  }
}
