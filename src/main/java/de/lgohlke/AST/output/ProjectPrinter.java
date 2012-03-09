package de.lgohlke.AST.output;

import java.io.PrintStream;

import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourcePackage;
import org.sonar.squid.api.SourceProject;

public class ProjectPrinter
{
  private final SourceProject        project;
  private final MutablePicoContainer pico;
  private final PrintStream          out = System.out;

  public ProjectPrinter(final SourceProject prj, final MutablePicoContainer pico)
  {
    this.pico = pico;
    this.project = prj;

    pico.addComponent("formatter", new Formatter());
  }

  public void print() throws NoSuchFieldException, IllegalAccessException
  {
    Formatter format = (Formatter) pico.getComponent("formatter");
    out.println(format.toString() + project.toString());
    format.plus();
    for (SourceCode p : project.getChildren())
    {
      new PackagePrinter((SourcePackage) p, pico).print();
    }
    // out.println(packages + " is package : " + packages.isType(SourcePackage.class));

    // for (SourceCode files : packages.getChildren())
    // {
    // out.println(files);
    //
    // for (SourceCode clazz : files.getChildren())
    // {
    // out.println(" C " + clazz);
    //
    // for (SourceCode clazz : files.getChildren())
    // {
    // }
    // }
    // }

  }
}
