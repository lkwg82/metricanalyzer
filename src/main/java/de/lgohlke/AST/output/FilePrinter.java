package de.lgohlke.AST.output;

import java.io.PrintStream;

import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.api.SourceClass;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceFile;
import org.sonar.squid.measures.Metric;

public class FilePrinter
{
  private final SourceFile           file;
  private final MutablePicoContainer pico;
  private final PrintStream          out = System.out;

  public FilePrinter(final SourceFile file, final MutablePicoContainer pico)
  {
    this.pico = pico;
    this.file = file;
  }

  @SuppressWarnings("deprecation")
  public void print() throws NoSuchFieldException, IllegalAccessException
  {
    Formatter format = (Formatter) pico.getComponent("formatter");
    out.println(format + " F " + file);
    out.println("File complexity:" + file.getInt(Metric.COMPLEXITY));
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
