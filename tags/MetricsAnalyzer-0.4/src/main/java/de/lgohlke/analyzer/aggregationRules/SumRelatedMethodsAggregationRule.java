package de.lgohlke.analyzer.aggregationRules;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.sonar.squid.api.SourceCode;
import org.sonar.squid.measures.Measurable;
import org.sonar.squid.measures.MetricDef;
import org.sonar.squid.measures.SumAggregationFormula;

import de.lgohlke.analyzer.MetricAccessor;

/**
 * aggregation of metrics by summing them up
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
public class SumRelatedMethodsAggregationRule extends MetricAggregationRuleAbstract
{
  /**
   * <p>
   * Constructor for SumAggregationRule.
   * </p>
   * 
   * @param metric
   *          a {@link org.sonar.squid.measures.MetricDef} object.
   */
  public SumRelatedMethodsAggregationRule(final MetricDef metric)
  {
    super(metric);
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
    @SuppressWarnings("rawtypes")
    List<Measurable> measurables = new ArrayList<Measurable>();
    SumAggregationFormula formula = new SumAggregationFormula();

    if (log.isDebugEnabled())
    {
      log.debug("metric : " + getMetric());
      log.debug(" current method " + accessor.retrieveTestMethod() + " has value : " + accessor.retrieveTestMethod().getDouble(getMetric()));
      if (accessor.getRelatedMethods().size() > 0)
      {
        log.debug("   aggregation ");
        for (SourceCode code : accessor.getRelatedMethods())
        {
          log.debug("     " + code.getName() + " has value : " + code.getDouble(getMetric()));
        }
      }
    }

    measurables.addAll(accessor.getRelatedMethods());

    accessor.retrieveTestMethod().add(getMetric(), formula.aggregate(getMetric(), measurables));
  }
}
