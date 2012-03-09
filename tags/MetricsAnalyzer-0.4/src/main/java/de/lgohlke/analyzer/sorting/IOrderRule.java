package de.lgohlke.analyzer.sorting;

import org.sonar.squid.api.SourceCode;

/**
 * interface for the rules how tests are ordered
 * 
 * @author lars
 */
public interface IOrderRule
{
  /**
   * weight tests
   * 
   * @param test2codeMap
   * @return
   */
  int weightMetric(final SourceCode code);
}
