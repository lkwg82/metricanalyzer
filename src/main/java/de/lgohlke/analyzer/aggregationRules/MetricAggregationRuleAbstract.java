package de.lgohlke.analyzer.aggregationRules;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.sonar.squid.measures.MetricDef;

import de.lgohlke.analyzer.MetricAccessor;

/**
 * <p>Abstract MetricAggregationRuleAbstract class.</p>
 *
 * @author lars
 * @version $Id: $
 */
@RequiredArgsConstructor
public abstract class MetricAggregationRuleAbstract
{
  @Getter
  private final MetricDef metric;


  /**
   * execute the aggregation of related metrics
   *
   * @param accessor a {@link de.lgohlke.analyzer.MetricAccessor} object.
   */
  public abstract void doAggregation(MetricAccessor accessor);
}
