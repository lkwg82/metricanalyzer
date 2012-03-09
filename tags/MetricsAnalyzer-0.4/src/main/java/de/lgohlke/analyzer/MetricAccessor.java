package de.lgohlke.analyzer;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceCodeSearchEngine;

import de.lgohlke.AST.Location;
import de.lgohlke.io.TestmethodContext;
import de.lgohlke.qdox.JavaMethodHashed;

/**
 * a simple access fascade for retrieving the metrics for some methods
 * 
 * @author lars
 * @version $Id: $
 */
@RequiredArgsConstructor
public class MetricAccessor
{
  private final SourceCodeSearchEngine sourceCodeSearchEngine;
  @Setter
  private TestmethodContext            testmethodContext = null;
  @Setter
  private String                       locationKey;

  /**
   * retrieves all method related to run this test (setup/teardown etc.)
   * 
   * @return a {@link java.util.List} object.
   */
  public List<SourceCode> getRelatedMethods()
  {
    if (testmethodContext == null)
    {
      throw new NullPointerException("please set first testmethodContext");
    }
    else
    {
      List<SourceCode> methods = new ArrayList<SourceCode>();

      for (JavaMethodHashed m : testmethodContext.getRelatedMethods())
      {
        Location location = testmethodContext.getLocationMap().get(m);
        new LocationFixer(sourceCodeSearchEngine).fixLocation(location);

        String relatedLocationKey = location.getKey();
        methods.add(sourceCodeSearchEngine.search(relatedLocationKey));
      }

      return methods;
    }
  }

  public int getRelatedMethodsSize(){
    return testmethodContext.getRelatedMethods().size();
  }

  /**
   * retrieves the actual testmethod
   * 
   * @return a {@link org.sonar.squid.api.SourceCode} object.
   */
  public SourceCode retrieveTestMethod()
  {
    return sourceCodeSearchEngine.search(locationKey);
  }

}
