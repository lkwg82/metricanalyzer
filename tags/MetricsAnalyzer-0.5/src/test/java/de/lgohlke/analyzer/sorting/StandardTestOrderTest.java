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
  static Map<String, SourceCode> test2codeMap;
  FailedTest                     failedTestA = FailedTest.createRawFailedTest("a", "fail");
  FailedTest                     failedTestB = FailedTest.createRawFailedTest("b", "fail");
  FailedTest                     failedTestC = FailedTest.createRawFailedTest("c", "fail");
  FailedTest                     failedTestD = FailedTest.createRawFailedTest("d", "fail");

  @BeforeClass
  public static void before()
  {
    test2codeMap = new HashMap<String, SourceCode>();

    SourceCode codeA = mock(SourceCode.class);
    SourceCode codeB = mock(SourceCode.class);
    SourceCode codeC = mock(SourceCode.class);
    SourceCode codeD = mock(SourceCode.class);

    when(codeA.getInt(ASTMetrics.AGGREGATE_MAX_DEFINITION_TYPE_DISTANCE)).thenReturn(5);
    when(codeA.getDouble(ASTMetrics.AGGREGATE_MEDIAN_DEFINITION_TYPE_DISTANCE)).thenReturn(3.5);
    when(codeA.getInt((MetricDef) Metric.STATEMENTS)).thenReturn(11);

    when(codeB.getInt(ASTMetrics.AGGREGATE_MAX_DEFINITION_TYPE_DISTANCE)).thenReturn(5);
    when(codeB.getDouble(ASTMetrics.AGGREGATE_MEDIAN_DEFINITION_TYPE_DISTANCE)).thenReturn(4.5);
    when(codeB.getInt((MetricDef) Metric.STATEMENTS)).thenReturn(12);

    when(codeC.getInt(ASTMetrics.AGGREGATE_MAX_DEFINITION_TYPE_DISTANCE)).thenReturn(3);
    when(codeC.getDouble(ASTMetrics.AGGREGATE_MEDIAN_DEFINITION_TYPE_DISTANCE)).thenReturn(4.5);
    when(codeC.getInt((MetricDef) Metric.STATEMENTS)).thenReturn(15);


    when(codeD.getInt(ASTMetrics.AGGREGATE_MAX_DEFINITION_TYPE_DISTANCE)).thenReturn(3);
    when(codeD.getDouble(ASTMetrics.AGGREGATE_MEDIAN_DEFINITION_TYPE_DISTANCE)).thenReturn(4.5);
    when(codeD.getInt((MetricDef) Metric.STATEMENTS)).thenReturn(5);

    test2codeMap.put("a#fail", codeA);
    test2codeMap.put("b#fail", codeB);
    test2codeMap.put("c#fail", codeC);
    test2codeMap.put("d#fail", codeD);
  }

  @Test
  public void simpleTest() throws Exception
  {

    List<FailedTest> expectedList = new ArrayList<FailedTest>();
    expectedList.add(failedTestA);
    expectedList.add(failedTestB);
    expectedList.add(failedTestC);
    expectedList.add(failedTestD);

    List<FailedTest> resultList = TestAnalysisOrderer.useMap(test2codeMap).orderWith(new StandardTestOrder());

    Assert.assertEquals(failedTestD, resultList.get(0));
    Assert.assertEquals(failedTestC, resultList.get(1));
    Assert.assertEquals(failedTestA, resultList.get(2));
    Assert.assertEquals(failedTestB, resultList.get(3));
  }

}
