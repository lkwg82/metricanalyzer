package de.lgohlke.syntaxhighlighter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import lombok.RequiredArgsConstructor;

import org.sonar.squid.api.SourceCode;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.ASTMetrics;

/**
 * <p>
 * MetricFormatter class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@RequiredArgsConstructor
public class MetricFormatter
{
  private final MetricDef                     metric;
  private final SourceCode                    code;

  private final static Set<MetricDef>         intMetrics    = new LinkedHashSet<MetricDef>(Arrays.asList(new MetricDef[] { Metric.LINES,//
      Metric.LINES_OF_CODE,//
      Metric.STATEMENTS,//
      ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION,//
      ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION,//
      ASTMetrics.AST_RELATED_METHODS,//
      ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE,//
      ASTMetrics.AST_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE,//
      ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE, //
      ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE, //
      Metric.COMMENT_LINES, Metric.HEADER_COMMENT_LINES,//
      Metric.DIT,//
      Metric.DISTANCE,//
      Metric.COMPLEXITY,//
      ASTMetrics.AST_NUMBER_OF_ASSERT_STATEMENTS,//
      ASTMetrics.AGGREGATE_SUM_VARIABLE_DEFINITION,//
      ASTMetrics.AGGREGATE_SUM_VARIABLE_DEFINITION_TYPE_DISTANCE,//
      ASTMetrics.AGGREGATE_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE }));

  private final static Map<MetricDef, String> doubleMetrics = new HashMap<MetricDef, String>()
      {
    private static final long serialVersionUID = 2594693215663102394L;

    {
      put(ASTMetrics.AGGREGATE_VARIABLE_DEFINITION_TYPE_DISTANCE_PER_LOC, "%.2f");
      put(ASTMetrics.AGGREGATE_MEDIAN_DEFINITION_TYPE_DISTANCE, "%.2f");
      put(ASTMetrics.AGGREGATE_MAX_DEFINITION_TYPE_DISTANCE, "%.2f");
    }
      };

      /** {@inheritDoc} */
      @Override
      public String toString()
      {
        String result;
        if (intMetrics.contains(metric))
        {
          int m = code.getInt(metric);
          result = String.valueOf(m < 10 ? "&nbsp;" + m : m);
        }
        else
        {
          if (doubleMetrics.containsKey(metric))
          {
            result = String.format(doubleMetrics.get(metric), code.getDouble(metric));
          }
          else
          {
            result = String.valueOf(code.getDouble(metric));
          }
        }
        return result;
      }

}
