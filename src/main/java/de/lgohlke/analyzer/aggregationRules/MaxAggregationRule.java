package de.lgohlke.analyzer.aggregationRules;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.analyzer.MetricAccessor;

/**
 * a data-list metrics, computes the max
 * 
 * @author lars
 */
public class MaxAggregationRule extends StatsAggregationRuleAbstract
{
  public MaxAggregationRule(final MetricDef aggregateMetric, final MetricDef... baseMetrics)
  {
    super(aggregateMetric, baseMetrics);
  }

  @Override
  public void doAggregation(final MetricAccessor accessor)
  {
    SourceCode testMethod = accessor.retrieveTestMethod();
    DescriptiveStatistics stats = prepareStats(testMethod);
    Double val = stats.getMax();
    if (Double.compare(val, Double.NaN) == 0)
    {
      val = 0D;
    }
    testMethod.setMeasure(getMetric(), val);
  }

}
