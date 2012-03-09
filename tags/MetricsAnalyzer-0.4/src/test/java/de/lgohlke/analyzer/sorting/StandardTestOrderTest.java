package de.lgohlke.analyzer.sorting;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.ASTMetrics;
import de.lgohlke.failedTestsfilter.FailedTest;

public class StandardTestOrderTest
{
  static Map<String, SourceCode> test2codeMap ;
  FailedTest failedTestA = FailedTest.createRawFailedTest("a", "fail");
  FailedTest failedTestB = FailedTest.createRawFailedTest("b", "fail");

  @BeforeClass
  public static void before() {
    test2codeMap = new HashMap<String, SourceCode>();

    SourceCode codeA = mock(SourceCode.class);
    SourceCode codeB = mock(SourceCode.class);

    when(codeA.getInt(ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION)).thenReturn(3);
    when(codeA.getInt(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION)).thenReturn(5);
    when(codeA.getInt(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE)).thenReturn(3);
    when(codeA.getInt(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE)).thenReturn(3);
    when(codeA.getInt(ASTMetrics.AST_RELATED_METHODS)).thenReturn(2);
    when(codeB.getInt((MetricDef) Metric.CA)).thenReturn(2);

    test2codeMap.put("a#fail", codeA);
    test2codeMap.put("b#fail", codeB);
  }

  @Test
  public void simpleTest() throws Exception
  {

    List<FailedTest> expectedList = new ArrayList<FailedTest>();
    expectedList.add(failedTestA);
    expectedList.add(failedTestB);

    List<FailedTest> resultList = TestAnalysisOrderer.useMap(test2codeMap).orderWith(new IOrderRule()
    {
      @Override
      public int weightMetric(final SourceCode code)
      {
        int weight = 0;

        weight += code.getInt((MetricDef) Metric.CA);

        return weight;
      }
    });

    Assert.assertEquals(failedTestA, resultList.get(0));
    Assert.assertEquals(failedTestB, resultList.get(1));
  }

}
