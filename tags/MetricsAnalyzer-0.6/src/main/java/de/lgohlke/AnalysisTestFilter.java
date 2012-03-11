package de.lgohlke;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.apache.commons.configuration.ConfigurationException;

import de.lgohlke.failedTestsfilter.FailedTest;
import de.lgohlke.failedTestsfilter.IFailedTestFilter;
import de.lgohlke.io.bo.TEST_TYPE;

/**
 * filterfor analysis
 * 
 * @author lars
 */
@RequiredArgsConstructor
public class AnalysisTestFilter
{

  private IFailedTestFilter filter;

  @Getter
  private final TEST_TYPE   type;

  private final Set<String> failedTestsClasses = new HashSet<String>();

  public IFailedTestFilter getFilter() throws ConfigurationException
  {
    if (filter == null)
    {
      filter = FailedTestFilterFactory.create(type);
    }
    return filter;
  }

  /**
   * checks if the type is <b>NOT</b> {@link TEST_TYPE#NONE}
   * 
   * @return
   */
  public boolean isActive()
  {
    return type != TEST_TYPE.NONE;
  }

  public Set<String> getFailedTestClasses() throws ConfigurationException, IOException
  {
    if (failedTestsClasses.isEmpty())
    {
      for (FailedTest test : getFilter().getFailedTests())
      {
        failedTestsClasses.add(test.getClazz());
      }
    }
    return failedTestsClasses;
  }

  public void setFilter(final IFailedTestFilter filter) throws ConfigurationException {
    if ( filter.getType() != type) {
      throw new ConfigurationException("the type of filter is wrong : " + filter.getType());
    }
    this.filter=filter;
  }
}
