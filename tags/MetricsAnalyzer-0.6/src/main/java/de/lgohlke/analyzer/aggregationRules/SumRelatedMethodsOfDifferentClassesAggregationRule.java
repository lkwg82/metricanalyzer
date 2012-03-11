package de.lgohlke.analyzer.aggregationRules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import lombok.extern.slf4j.Slf4j;

import org.sonar.squid.api.SourceClass;
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
public class SumRelatedMethodsOfDifferentClassesAggregationRule extends MetricAggregationRuleAbstract
{
  /**
   * <p>
   * Constructor for SumAggregationRule.
   * </p>
   * 
   * @param metric
   *          a {@link org.sonar.squid.measures.MetricDef} object.
   */
  public SumRelatedMethodsOfDifferentClassesAggregationRule(final MetricDef metric)
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

    Set<String> accessClasses = new TreeSet<String>();
    String clazz = accessor.retrieveTestMethod().getParent(SourceClass.class).getKey();
    accessClasses.add(clazz);
    // aggregating different class contexts with class vars
    for (SourceCode m : accessor.getRelatedMethods())
    {
      // log.info("  " + m + " class " + m.getMethod().getParentClass());
      if (accessClasses.add(m.getParent(SourceClass.class).getKey()))
      {
        measurables.add(m);
      }
    }

    accessor.retrieveTestMethod().add(getMetric(), new SumAggregationFormula().aggregate(getMetric(), measurables));
  }
}
