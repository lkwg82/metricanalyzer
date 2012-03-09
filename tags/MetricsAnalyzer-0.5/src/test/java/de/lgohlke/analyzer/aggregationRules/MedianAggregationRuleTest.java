package de.lgohlke.analyzer.aggregationRules;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceMethod;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.analyzer.MetricAccessor;

public class MedianAggregationRuleTest
{
  MetricAccessor accessor;

  @Before
  public void before()
  {
    accessor = mock(MetricAccessor.class);
    SourceCode code = new SourceMethod("key");

    when(accessor.retrieveTestMethod()).thenReturn(code);

    List<Double> list = new ArrayList<Double>();
    list.add(0D);
    list.add(1D);
    list.add(2D);
    list.add(3D);
    code.addData(Metric.CA, list);
  }

  @Test
  public void test()
  {
    MetricAggregationRuleAbstract rule = new MedianAggregationRule(Metric.LINES, Metric.CA);
    rule.doAggregation(accessor);

    Assert.assertEquals(1.5, accessor.retrieveTestMethod().getDouble((MetricDef) Metric.LINES));
  }
}
