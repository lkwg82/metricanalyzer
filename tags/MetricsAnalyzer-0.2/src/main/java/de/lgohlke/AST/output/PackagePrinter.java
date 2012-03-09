package de.lgohlke.AST.output;

import java.io.PrintStream;

import org.picocontainer.MutablePicoContainer;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.api.SourceFile;
import org.sonar.squid.api.SourcePackage;

public class PackagePrinter
{
  private final SourcePackage        _package;
  private final MutablePicoContainer pico;
  private final PrintStream          out = System.out;

  public PackagePrinter(final SourcePackage _package, final MutablePicoContainer pico)
  {
    this.pico = pico;
    this._package = _package;
  }

  public void print() throws NoSuchFieldException, IllegalAccessException
  {
    Formatter format = (Formatter) pico.getComponent("formatter");
    out.println(format + " P " + _package);

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
