package de.lgohlke.analyzer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.ASTMetrics;

public final class MetricsWorthToAnalyze
{

  public static final List<MetricDef> LIST = Collections.unmodifiableList(Arrays.asList(new MetricDef[]
                                           {//
                                            //
      ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION, //
      ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE, //
      Metric.COMPLEXITY, //
      Metric.DISTANCE, //
      Metric.DIT, //
      Metric.HEADER_COMMENT_LINES, //
      Metric.COMMENT_LINES, //
      Metric.INSTABILITY, //
      Metric.LINES_OF_CODE, //
      Metric.LINES, //
      Metric.STATEMENTS                       }));

  private MetricsWorthToAnalyze()
  {
    // ok
  }
}
