package de.lgohlke.AST.output;

import java.io.PrintStream;

import lombok.RequiredArgsConstructor;

import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.api.SourceClass;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceFile;
import org.sonar.squid.measures.Metric;
import org.sonar.squid.measures.MetricDef;

/**
 * <p>FilePrinter class.</p>
 *
 * @author lars
 * @version $Id: $
 */
@RequiredArgsConstructor
public class FilePrinter
{
  private final SourceFile           file;
  private final MutablePicoContainer pico;
  private final PrintStream          out = System.out;

  /**
   * <p>print.</p>
   */
  public void print()
  {
    Formatter format = (Formatter) pico.getComponent("formatter");
    out.println(format + " F " + file);
    out.println(format + " File complexity:" + file.getInt((MetricDef) Metric.COMPLEXITY));

    if (file.hasChildren())
    {
      for (SourceCode clazz : file.getChildren())
      {
        format.plus();
        if (clazz.isType(SourceClass.class))
        {
          new ClassPrinter((SourceClass) clazz, pico).print();
        }
        format.minus();
      }
    }
  }
}
