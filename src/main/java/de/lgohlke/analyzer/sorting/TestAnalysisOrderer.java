package de.lgohlke.analyzer.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.RequiredArgsConstructor;

import org.sonar.squid.api.SourceCode;

import de.lgohlke.failedTestsfilter.FailedTest;

/**
 * do the sorting of the tests by a ruleset given
 * 
 * @author lars
 */
public class TestAnalysisOrderer
{
  @RequiredArgsConstructor
  public static class OrderWith
  {
    private final Map<String, SourceCode> test2codeMap;

    public List<FailedTest> orderWith(final IOrderRule orderRule)
    {
      return orderTests(test2codeMap, orderRule);
    }

    private List<FailedTest> orderTests(final Map<String, SourceCode> test2codeMap, final IOrderRule orderRule)
    {
      final Map<FailedTest, Integer> weights = new HashMap<FailedTest, Integer>();
      List<FailedTest> orderedList = new ArrayList<FailedTest>();
      for (Entry<String, SourceCode> entry : test2codeMap.entrySet())
      {
        String key = entry.getKey();
        SourceCode code = entry.getValue();

        String[] parts = key.split("#");

        FailedTest failedTest = FailedTest.createRawFailedTest(parts[0], parts[1]);
        weights.put(failedTest, orderRule.weightMetric(code));

        orderedList.add(failedTest);
      }

      // sort tests according their weights
      Collections.sort(orderedList, new Comparator<FailedTest>()
          {
        @Override
        public int compare(final FailedTest a, final FailedTest b)
        {
          int weightA = weights.get(a);
          int weightB = weights.get(b);

          return weightA == weightB ? 0 : weightA < weightB ? -1 : 1;
        }
          });
      return orderedList;
    }
  }

  public static OrderWith useMap(final Map<String, SourceCode> test2codeMap)
  {
    return new OrderWith(test2codeMap);
  }

}
