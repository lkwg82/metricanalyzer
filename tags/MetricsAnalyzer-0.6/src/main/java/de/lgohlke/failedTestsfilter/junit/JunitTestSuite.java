package de.lgohlke.failedTestsfilter.junit;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import de.lgohlke.failedTestsfilter.FailedTest;
import de.lgohlke.failedTestsfilter.Property;

@Data
@XStreamAlias("testsuite")
public class JunitTestSuite
{

  @XStreamAlias("properties")
  private final List<Property> properties = new ArrayList<Property>();

  @Data
  public static class TestCase
  {
    @XStreamAsAttribute
    private String  name;
    @XStreamAsAttribute
    private String  classname;

    private Failure failure;
  }

  @XStreamAlias("failure")
  public static class Failure
  {

  }

  @XStreamImplicit(itemFieldName = "testcase")
  private final List<TestCase> testcases = new ArrayList<TestCase>();

  public List<FailedTest> getFailedTests()
  {
    List<FailedTest> result = new ArrayList<FailedTest>();
    for (TestCase tc : testcases)
    {
      if (tc.getFailure() != null)
      {
        result.add(FailedTest.createFromJunitTestCase(tc));
      }
    }
    return result;
  }

}
