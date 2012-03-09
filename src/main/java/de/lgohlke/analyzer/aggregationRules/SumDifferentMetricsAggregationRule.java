package de.lgohlke.analyzer.aggregationRules;

import org.sonar.squid.api.SourceCode;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.analyzer.MetricAccessor;

/**
 * aggregation of metrics by summing them up
 * 
 * @author lars
 * @version $Id: $
 */
//@Slf4j
/**
 * @author lars
 *
 */
public class SumDifferentMetricsAggregationRule extends MetricAggregationRuleAbstract
{
  private final MetricDef[] metrics;

  /**
   * @param metric
   * @param metrics
   */
  public SumDifferentMetricsAggregationRule(final MetricDef metric, final MetricDef ... metrics)
  {
    super(metric);
    this.metrics = metrics;
  }

  /*
   * (non-Javadoc)
   * @see
   * de.lgohlke.analyzer.aggregationRules.MetricAggregationRuleAbstract#doAggregation(de.lgohlke.analyzer.MetricAccessor
   * )
   */
  @Override
  public void doAggregation(final MetricAccessor accessor)
  {
    SourceCode testMethod = accessor.retrieveTestMethod();

    Double count = 0D;
    for (MetricDef m : metrics)
    {
      count += testMethod.getDouble(m);
    }

    testMethod.setMeasure(getMetric(), count);
  }
}
