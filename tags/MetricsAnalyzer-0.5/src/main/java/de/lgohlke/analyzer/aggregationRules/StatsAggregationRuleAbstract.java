package de.lgohlke.analyzer.aggregationRules;

import java.util.List;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.measures.MetricDef;

/**
 * abstract aggregation rule to wrap the list into an instance {@see DescriptiveStatistics}
 * 
 * @author lars
 */
public abstract class StatsAggregationRuleAbstract extends MetricAggregationRuleAbstract
{
  private MetricDef[] baseMetrics;

  public StatsAggregationRuleAbstract(final MetricDef metric, final MetricDef... baseMetrics)
  {
    super(metric);
    this.baseMetrics = baseMetrics;
  }

  protected final DescriptiveStatistics prepareStats(final SourceCode code)
  {
    DescriptiveStatistics stats = new DescriptiveStatistics();

    for (MetricDef m : baseMetrics)
    {
      @SuppressWarnings("unchecked")
      List<Double> list = (List<Double>) code.getData(m);

      if ((list == null) || list.isEmpty())
      {
        // ok
      }
      else
      {
        for (Double i : list)
        {
          stats.addValue(i);
        }
      }
    }
    return stats;
  }

}
