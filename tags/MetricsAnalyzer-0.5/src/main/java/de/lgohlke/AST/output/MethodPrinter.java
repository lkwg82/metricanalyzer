package de.lgohlke.AST.output;

import java.io.PrintStream;

import lombok.RequiredArgsConstructor;

import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.Squid;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceMethod;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.ASTMetrics;

/**
 * <p>MethodPrinter class.</p>
 *
 * @author lars
 * @version $Id: $
 */
@RequiredArgsConstructor
public class MethodPrinter
{
  private final SourceMethod         method;
  private final MutablePicoContainer pico;
  private final PrintStream          out = System.out;

  /**
   * <p>print.</p>
   */
  public void print()
  {
    Formatter format = (Formatter) pico.getComponent("formatter");
    out.println(format + " M " + method.getKey());

    format.plus();

    Squid index = (Squid) pico.getComponent("singleton");
    if (index != null)
    {
      SourceCode sc = index.search(method.getKey());

      out.println(format + " comple: " + sc.getInt((MetricDef) Metric.COMPLEXITY));
      out.println(format + " distance: " + sc.getInt(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));
      out.println(format + " number: " + sc.getInt(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION));
    }
    format.minus();
  }
}
