package de.lgohlke.failedTestsfilter.testng;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import de.lgohlke.failedTestsfilter.FailedTest;

@XStreamAlias("suite")
@Data
public class TestngTestSuite
{
  @XStreamImplicit(keyFieldName = "test")
  private final List<Test> tests = new ArrayList<Test>();

  @XStreamAlias("test")
  @Data
  public static class Test
  {
    @XStreamAlias("classes")
    private final List<Clazz> classes = new ArrayList<Clazz>();
  }

  @XStreamAlias("class")
  @Data
  public static class Clazz
  {
    @XStreamAsAttribute
    private String       name;

    @XStreamAlias("methods")
    private final Method methods;
  }

  @XStreamAlias("method")
  @Data
  public static class Method
  {
    @XStreamImplicit(keyFieldName = "include")
    private final List<Include> includes = new ArrayList<Include>();
  }

  @XStreamAlias("include")
  @Data
  public static class Include
  {
    @XStreamAsAttribute
    private String name;
  }

  public List<FailedTest> getFailedTests()
  {
    List<FailedTest> result = new ArrayList<FailedTest>();
    for (Test t : tests)
    {
      for (Clazz c : t.getClasses())
      {
        for (Include i : c.getMethods().getIncludes())
        {
          String method = i.getName();

          result.add(FailedTest.createRawFailedTest(c.getName(), method));
        }
      }
    }
    return result;
  }

}
