package de.lgohlke.analyzer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.ASTMetrics;

/**
 * <p>
 * MetricsWorthToAnalyze class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
public final class MetricsWorthToAnalyze
{

  /** Constant <code>LIST</code> */
  public static final List<MetricDef> LIST = Collections.unmodifiableList(Arrays.asList(new MetricDef[] {//
      //
      ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION, //
      ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION, //
      ASTMetrics.AGGREGATE_VARIABLE_DEFINITION,//
      //
      ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE, //
      ASTMetrics.AST_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE, //
      ASTMetrics.AGGREGATE_VARIABLE_DEFINITION_TYPE_DISTANCE,//
      //
      ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE, //
      ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE, //
      ASTMetrics.AGGREGATE_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE,//

      ASTMetrics.AST_RELATED_METHODS,//
      Metric.COMPLEXITY, //
      // Metric.DISTANCE, //
      // Metric.DIT, //
      // Metric.HEADER_COMMENT_LINES, //
      // Metric.COMMENT_LINES, //
      // Metric.INSTABILITY, //
      Metric.LINES_OF_CODE, //
      Metric.LINES, //
      Metric.STATEMENTS,//
      ASTMetrics.AST_NUMBER_OF_ASSERT_STATEMENTS,//
      ASTMetrics.AGGREGATE_VARIABLE_DEFINITION_TYPE_DISTANCE_PER_LOC }));

  private MetricsWorthToAnalyze()
  {
    // ok
  }
}
