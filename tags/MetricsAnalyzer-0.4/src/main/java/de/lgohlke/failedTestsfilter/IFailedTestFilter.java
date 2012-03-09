package de.lgohlke.failedTestsfilter;

import java.io.IOException;
import java.util.List;

import de.lgohlke.io.bo.TEST_TYPE;

public interface IFailedTestFilter
{
  /**
   * show the type
   * 
   * @return
   */
  public TEST_TYPE getType();

  /**
   * fresh seeking
   * 
   * @return
   * @throws IOException
   */
  List<FailedTest> findFailedTests() throws IOException;

  /**
   * lazy return already found results
   * 
   * @return
   * @throws IOException
   */
  List<FailedTest> getFailedTests() throws IOException;
}
