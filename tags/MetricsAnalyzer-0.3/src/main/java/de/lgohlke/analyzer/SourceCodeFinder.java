package de.lgohlke.analyzer;

import java.util.ArrayList;
import java.util.Collection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.sonar.squid.api.Query;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceCodeSearchEngine;
import org.sonar.squid.api.SourceFile;
import org.sonar.squid.api.SourceMethod;

import com.thoughtworks.qdox.model.JavaMethod;

/**
 * retrieves the {@link org.sonar.squid.api.SourceCode} from {@link org.sonar.squid.api.SourceProject}
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
@RequiredArgsConstructor
public class SourceCodeFinder
{
  private final SourceCodeSearchEngine searchEngine;

  /**
   * <p>
   * findSourceCodeFor.
   * </p>
   * 
   * @param test
   *          a {@link de.lgohlke.io.bo.TestMethod} object.
   * @return a {@link org.sonar.squid.api.SourceCode} object.
   */
  public SourceCode findSourceCodeFor(final JavaMethod test)
  {
    final String testFile = test.getParentClass().getSource().getURL().getFile();
    if (log.isDebugEnabled())
    {
      log.debug("seeking " + test);
    }
    Collection<SourceCode> results = searchEngine.search(new Query()
    {
      @Override
      public boolean match(final SourceCode unit)
      {
        if (unit.isType(SourceMethod.class) && (unit.getStartAtLine() == test.getLineNumber()))
        {
          String file = unit.getParent(SourceFile.class).getKey();
          return testFile.endsWith(file);
        }
        else
        {
          return false;
        }
      }
    });

    if (results.size() == 0)
    {
      log.error("could not find : " + test);
    }
    else
    {
      if (results.size() == 1)
      {
        return new ArrayList<SourceCode>(results).get(0);
      }
      else
      {
        log.error("results are more than exact one, so filter is not sharp enough for test: " + test);
      }
    }
    return null;
  }
}
