package de.lgohlke.AST.output;

import java.io.PrintStream;
import java.util.Collection;

import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.Squid;
import org.sonar.squid.api.SourceClass;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceMethod;
import org.sonar.squid.indexer.QueryByType;
import org.sonar.squid.measures.Metric;

public class ClassPrinter
{
  private final SourceClass          clazz;
  private final MutablePicoContainer pico;
  private PrintStream                out = System.out;

  public ClassPrinter(final SourceClass clazz, final MutablePicoContainer pico)
  {
    this.pico = pico;
    this.clazz = clazz;
  }

  @SuppressWarnings("deprecation")
  public void print() throws NoSuchFieldException, IllegalAccessException
  {

    Squid index = (Squid) pico.getComponent("singleton");
    Formatter format = (Formatter) pico.getComponent("formatter");

    out.println(format + " C " + clazz);
    out.println("Class complexity:" + clazz.getInt(Metric.COMPLEXITY));
    out.println(" imports: ");
    // Object data = clazz.getData(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE);

    Collection<SourceCode> results = index.search(new QueryByType(SourceMethod.class));
    for (SourceCode member : results)
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

  public void setOut(final PrintStream out)
  {
    this.out = out;
  }

  public PrintStream getOut()
  {
    return out;
  }
}
