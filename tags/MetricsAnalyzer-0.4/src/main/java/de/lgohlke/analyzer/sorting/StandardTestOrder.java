package de.lgohlke.analyzer.sorting;

import org.sonar.squid.api.SourceCode;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

public class StandardTestOrder implements IOrderRule
{

  @Override
  public int weightMetric(final SourceCode code)
  {
    int weight = 0;

    weight += code.getInt((MetricDef) Metric.CA);

    return  weight;
  }

}
