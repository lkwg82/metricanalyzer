package de.lgohlke.analyzer;

import org.sonar.squid.api.SourceClass;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceFile;
import org.sonar.squid.api.SourceMethod;
import org.sonar.squid.api.SourcePackage;
import org.sonar.squid.api.SourceProject;

import de.lgohlke.io.bo.TestMethod;

/**
 * retrieves the {@link org.sonar.squid.api.SourceCode} from {@link org.sonar.squid.api.SourceProject}
 * 
 * @author lars
 */
public class SourceCodeFinder
{

  private final SourceProject project;

  public SourceCodeFinder(final SourceProject project)
  {
    this.project = project;
  }

  public SourceCode findSourceCodeFor(final TestMethod test)
  {
    // JavaClass clazz = test.getMethod().getParentClass();
    //
    // String packageName = clazz.getPackageName().replace(".", "/");
    // String className = packageName + "/" + clazz.getName().replace(".", "/");

    SourceCode code = findSourcecode(project, SourceMethod.class, test.getMethod().toString(), test.getMethod().getLineNumber());

    return code;
  }

  private SourceCode findSourcecode(final SourceCode code, final Class<? extends SourceCode> type, final String identifier, final int startLine)
  {
    SourceCode result = null;

    if (code.isType(SourceProject.class))
    {
      result = compareTypeAndGoOn(result, SourceProject.class, type, code, identifier);
    }
    else if (code.isType(SourcePackage.class))
    {
      result = compareTypeAndGoOn(result, SourcePackage.class, type, code, identifier);
    }
    else if (code.isType(SourceFile.class))
    {
      result = compareTypeAndGoOn(result, SourceFile.class, type, code, identifier);
    }
    else if (code.isType(SourceClass.class))
    {
      result = compareTypeAndGoOn(result, SourceClass.class, type, code, identifier);
    }
    else if (code.isType(SourceMethod.class) && (code.getStartAtLine() == startLine))
    {
      String currentClass = code.getParent().getKey().replace("/", ".");

      // roughly testing
      if (identifier.contains(currentClass + "."))
      {
        // System.out.println(identifier + " " + code + " i" + startLine + ":" + code.getStartAtLine());

        String name = code.getName().replaceAll("\\(.*", "");

        // exact checking
        if (identifier.contains(currentClass + "." + name + "("))
        {
          result = code;
        }
      }
    }

    return result;
  }

  private SourceCode compareTypeAndGoOn(final SourceCode formerResult, final Class<? extends SourceCode> actualType,
      final Class<? extends SourceCode> expectedType, final SourceCode s, final String identifier)
  {
    SourceCode result = formerResult;
    if (result == null)
    {
      if ((actualType.equals(expectedType)))
      {
        String key = s.getKey();
        if (key.equals(identifier))
        {
          result = s;
        }
        else
        {
          result = null;
        }
      }
      else
      {
        if (s.getChildren() != null)
        {
          for (SourceCode child : s.getChildren())
          {
            if (result == null)
            {
              result = findSourcecode(child, expectedType, identifier, child.getStartAtLine());
            }
          }
        }
      }
    }
    return result;
  }
}
