package de.lgohlke.AST;

import org.sonar.squid.measures.CalculatedMetricFormula;
import org.sonar.squid.measures.MetricDef;

/**
 * <p>
 * ASTMetrics class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
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
  AST_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE
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
  },
  AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION
  {

    @Override
    public String getShortName()
    {
      return "# var defs";
    }
  },
  AST_RELATED_METHODS
  {

    @Override
    public String getShortName()
    {
      return "# of related methods";
    }

  },
  AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE
  {

    @Override
    public String getShortName()
    {
      return "# of var defs with non-zero type distance";
    }
  },
  AST_NUMBER_OF_ASSERT_STATEMENTS
  {

    @Override
    public String getShortName()
    {
      return "# asserts";
    }
  },
  AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE
  {

    @Override
    public String getShortName()
    {
      return "# of var defs with non-zero type distance";
    }
  },
  /**
   * sum of the {@link ASTMetrics#AST_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE} and
   * {@link ASTMetrics#AST_VARIABLE_DEFINITION_TYPE_DISTANCE}
   */
  AGGREGATE_SUM_VARIABLE_DEFINITION
  {

    @Override
    public String getShortName()
    {
      return "# var defs (class + methods)";
    }

  },
  AGGREGATE_VARIABLE_DEFINITION_TYPE_DISTANCE
  {

    @Override
    public String getShortName()
    {
      return "# var type defs (class + methods)";
    }
  },
  AGGREGATE_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE
  {

    @Override
    public String getShortName()
    {
      return "# of var defs with non-zero type distance (class + method)";
    }
  },
  AGGREGATE_VARIABLE_DEFINITION_TYPE_DISTANCE_PER_LOC
  {

    @Override
    public String getShortName()
    {
      return "density of type distance per loc";
    }
  };

  /** Constant <code>aggregateIfThereIsAlreadyAValue=true</code> */
  private final static boolean           aggregateIfThereIsAlreadyAValue = true;
  // private final static AggregationFormula aggregationFormula = new SumAggregationFormula();
  private static CalculatedMetricFormula formula                         = null;

  /**
   * <p>
   * aggregateIfThereIsAlreadyAValue.
   * </p>
   * 
   * @return a boolean.
   */
  @Override
  public boolean aggregateIfThereIsAlreadyAValue()
  {
    return aggregateIfThereIsAlreadyAValue;
  }

  /**
   * <p>
   * getCalculatedMetricFormula.
   * </p>
   * 
   * @return a {@link org.sonar.squid.measures.CalculatedMetricFormula} object.
   */
  @Override
  public CalculatedMetricFormula getCalculatedMetricFormula()
  {
    return formula;
  }

  /**
   * <p>
   * getName.
   * </p>
   * 
   * @return a {@link java.lang.String} object.
   */
  @Override
  public String getName()
  {
    return name();
  }

  /**
   * <p>
   * getShortName.
   * </p>
   * 
   * @return a {@link java.lang.String} object.
   */
  public abstract String getShortName();

  /**
   * <p>
   * isCalculatedMetric.
   * </p>
   * 
   * @return a boolean.
   */
  @Override
  public boolean isCalculatedMetric()
  {
    return formula != null;
  }

  /**
   * <p>
   * isThereAggregationFormula.
   * </p>
   * 
   * @return a boolean.
   */
  @Override
  public boolean isThereAggregationFormula()
  {
    return false; // !(aggregationFormula instanceof NoAggregationFormula);
  }
}
