package de.lgohlke.AST.output;

import java.io.PrintStream;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourcePackage;
import org.sonar.squid.api.SourceProject;

/**
 * <p>ProjectPrinter class.</p>
 *
 * @author lars
 * @version $Id: $
 */
public class ProjectPrinter
{
  private final SourceProject        project;
  private final MutablePicoContainer pico;
  private final PrintStream          out = System.out;

  /**
   * <p>Constructor for ProjectPrinter.</p>
   *
   * @param prj a {@link org.sonar.squid.api.SourceProject} object.
   * @param pico a {@link org.picocontainer.MutablePicoContainer} object.
   */
  public ProjectPrinter(final SourceProject prj, final MutablePicoContainer pico)
  {
    this.pico = pico;
    this.project = prj;

    pico.addComponent("formatter", new Formatter());
  }

  /**
   * <p>Constructor for ProjectPrinter.</p>
   *
   * @param prj a {@link org.sonar.squid.api.SourceProject} object.
   */
  public ProjectPrinter(final SourceProject prj)
  {
    this(prj, new DefaultPicoContainer());
  }

  /**
   * <p>print.</p>
   */
  public void print()
  {
    Formatter format = (Formatter) pico.getComponent("formatter");
    out.println(format.toString() + project.toString());
    format.plus();
    if (project.hasChildren())
    {
      for (SourceCode p : project.getChildren())
      {
        new PackagePrinter((SourcePackage) p, pico).print();
      }
    }
  }
}
