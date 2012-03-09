package de.lgohlke.AST.output;

import java.io.PrintStream;

import lombok.RequiredArgsConstructor;

import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.api.SourceClass;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceMethod;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

/**
 * <p>ClassPrinter class.</p>
 *
 * @author lars
 * @version $Id: $
 */
@RequiredArgsConstructor
public class ClassPrinter
{
  private final SourceClass          clazz;
  private final MutablePicoContainer pico;
  private final PrintStream          out = System.out;

  /**
   * <p>print.</p>
   */
  public void print()
  {

    // Squid index = (Squid) pico.getComponent("singleton");
    Formatter format = (Formatter) pico.getComponent("formatter");

    out.println(format + " C " + clazz);
    out.println(format + " Class complexity:" + clazz.getInt((MetricDef) Metric.COMPLEXITY));
    // out.println(" imports: ");

    if (clazz.hasChildren())
    {
      for (SourceCode member : clazz.getChildren())
      {
        format.plus();
        if (member.isType(SourceClass.class))
        {
          new ClassPrinter((SourceClass) member, pico).print();
        }
        else if (member.isType(SourceMethod.class))
        {
          SourceMethod m = (SourceMethod) member;
          if (m.toString().startsWith(clazz.toString()))
          {
            new MethodPrinter(m, pico).print();
          }
        }
        format.minus();
      }
    }
  }
}
