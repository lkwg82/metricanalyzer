package de.lgohlke.analyzer.sorting;

import org.sonar.squid.api.SourceCode;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.ASTMetrics;

public class StandardTestOrder implements IOrderRule
{

  @Override
  public int weightMetric(final SourceCode code)
  {
    int weight = 0;

    weight += code.getInt(ASTMetrics.AGGREGATE_MAX_DEFINITION_TYPE_DISTANCE) * 100 * 1000;
    weight += code.getDouble(ASTMetrics.AGGREGATE_MEDIAN_DEFINITION_TYPE_DISTANCE) * 1000;
    weight += code.getInt((MetricDef) Metric.STATEMENTS);
    return weight;
  }

}
