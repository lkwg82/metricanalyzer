package de.lgohlke.analyzer.aggregationRules;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceMethod;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.analyzer.MetricAccessor;

public class SumDifferentMetricsAggregationRuleTest
{
  MetricAccessor accessor;

  @Before
  public void before()
  {
    accessor = mock(MetricAccessor.class);
    SourceCode code = new SourceMethod("key");

    when(accessor.retrieveTestMethod()).thenReturn(code);

    code.setMeasure(Metric.LCOM4,2D);
    code.setMeasure(Metric.BRANCHES,5D);
  }

  @Test
  public void test()
  {
    MetricAggregationRuleAbstract rule = new SumDifferentMetricsAggregationRule(Metric.LINES, Metric.LCOM4, Metric.BRANCHES);
    rule.doAggregation(accessor);

    Assert.assertEquals(7D, accessor.retrieveTestMethod().getDouble((MetricDef) Metric.LINES));
  }
}
