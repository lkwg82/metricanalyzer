package de.lgohlke.analyzer.aggregationRules;

import lombok.extern.slf4j.Slf4j;

import org.sonar.squid.api.SourceClass;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.ASTMetrics;
import de.lgohlke.analyzer.MetricAccessor;

/**
 * aggregation of metrics by summing them up
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
public class SetRelatedMethodsCountAggregationRule extends MetricAggregationRuleAbstract
{
  /**
   * <p>
   * Constructor for SumAggregationRule.
   * </p>
   * 
   * @param metric
   *          a {@link org.sonar.squid.measures.MetricDef} object.
   */
  public SetRelatedMethodsCountAggregationRule(final MetricDef metric)
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
    if (log.isDebugEnabled())
    {
      String identifier = accessor.retrieveTestMethod().getParent(SourceClass.class).getName() + ".";
      identifier += accessor.retrieveTestMethod().getName();
      log.debug("related methods for test : " + identifier);
      for (SourceCode method : accessor.getRelatedMethods())
      {
        log.debug(" " + method);
      }
    }
    accessor.retrieveTestMethod().setMeasure(ASTMetrics.AST_RELATED_METHODS, accessor.getRelatedMethodsSize());
  }
}
