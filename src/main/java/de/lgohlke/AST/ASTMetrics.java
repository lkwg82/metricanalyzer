package de.lgohlke.AST;

import org.sonar.squid.measures.AggregationFormula;
import org.sonar.squid.measures.CalculatedMetricFormula;
import org.sonar.squid.measures.MetricDef;
import org.sonar.squid.measures.NoAggregationFormula;
import org.sonar.squid.measures.SumAggregationFormula;

public enum ASTMetrics implements MetricDef
{
  AST_VARIABLE_DEFINITION_TYPE_DISTANCE
  {
    @Override
    public String getShortName()
    {
      return "type distance";
    }
  },
  AST_NUMBER_OF_VARIABLE_DEFINITION
  {

    @Override
    public String getShortName()
    {
      return "# var defs";
    }
  };

  private final static boolean            aggregateIfThereIsAlreadyAValue = true;
  private final static AggregationFormula aggregationFormula              = new SumAggregationFormula();
  private static CalculatedMetricFormula  formula                         = null;

  public boolean aggregateIfThereIsAlreadyAValue()
  {
    return aggregateIfThereIsAlreadyAValue;
  }

  public CalculatedMetricFormula getCalculatedMetricFormula()
  {
    return formula;
  }

  public String getName()
  {
    return name();
  }

  public abstract String getShortName();

  public boolean isCalculatedMetric()
  {
    return formula != null;
  }

  public boolean isThereAggregationFormula()
  {
    return !(aggregationFormula instanceof NoAggregationFormula);
  }
}
