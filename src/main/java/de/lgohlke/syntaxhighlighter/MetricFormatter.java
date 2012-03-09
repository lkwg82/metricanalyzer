package de.lgohlke.syntaxhighlighter;

import java.util.Arrays;
import java.util.LinkedHashSet;
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
  private final MetricDef             metric;
  private final SourceCode            code;

  private final static Set<MetricDef> intMetrics = new LinkedHashSet<MetricDef>(Arrays.asList(new MetricDef[] { Metric.LINES,//
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
      Metric.COMPLEXITY                         }));

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    String result;
    if (intMetrics.contains(metric))
    {
      result = String.valueOf(code.getInt(metric));
    }
    else
    {
      result = String.valueOf(code.getDouble(metric));
    }
    return result;
  }

}
