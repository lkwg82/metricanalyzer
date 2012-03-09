package de.lgohlke.AST.output;

import java.io.PrintStream;

import lombok.RequiredArgsConstructor;

import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceFile;
import org.sonar.squid.api.SourcePackage;

/**
 * <p>PackagePrinter class.</p>
 *
 * @author lars
 * @version $Id: $
 */
@RequiredArgsConstructor
public class PackagePrinter
{
  private final SourcePackage        _package;
  private final MutablePicoContainer pico;
  private final PrintStream          out = System.out;


  /**
   * <p>print.</p>
   */
  public void print()
  {
    Formatter format = (Formatter) pico.getComponent("formatter");
    out.println(format + " P " + _package);

    if (_package.hasChildren())
    {
      for (SourceCode file : _package.getChildren())
      {
        format.plus();
        if (file.isType(SourceFile.class))
        {
          new FilePrinter((SourceFile) file, pico).print();
        }
        format.minus();
      }
    }
  }
}
