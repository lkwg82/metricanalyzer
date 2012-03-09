package de.lgohlke.AST.output;

import java.io.PrintStream;

import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.Squid;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceMethod;
import org.sonar.squid.measures.Metric;

import de.lgohlke.AST.ASTMetrics;

public class MethodPrinter
{
  private final SourceMethod         method;
  private final MutablePicoContainer pico;
  private final PrintStream          out = System.out;

  public MethodPrinter(final SourceMethod method, final MutablePicoContainer pico)
  {
    this.pico = pico;
    this.method = method;
  }

  @SuppressWarnings("deprecation")
  public void print()
  {
    Formatter format = (Formatter) pico.getComponent("formatter");
    Squid index = (Squid) pico.getComponent("singleton");
    SourceCode sc = index.search(method.getKey());
    out.println(format + " M " + method);

    format.plus();
    out.println(format + " comple: " + sc.getInt(Metric.COMPLEXITY));
    out.println(format + " distance: " + sc.getInt(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE));
    out.println(format + " number: " + sc.getInt(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION));
    format.minus();
  }
}
