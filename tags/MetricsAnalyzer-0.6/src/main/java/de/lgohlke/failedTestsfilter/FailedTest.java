package de.lgohlke.failedTestsfilter;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import de.lgohlke.failedTestsfilter.junit.JunitTestSuite.TestCase;

@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
@Data
public class FailedTest
{
  private final String clazz;
  private final String method;

  public static FailedTest createFromJunitTestCase(final TestCase tc)
  {
    return new FailedTest(tc.getClassname(), tc.getName());
  }
  public static FailedTest createRawFailedTest(final String clazz, final String method)
  {
    return new FailedTest(clazz, method);
  }

}
