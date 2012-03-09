package de.lgohlke.AST.output;

import java.io.PrintStream;

import de.lgohlke.AST.Registry;

public class RegistryPrinter
{
  private final PrintStream out = System.out;
  private final Registry    registry;

  public RegistryPrinter(final Registry registry)
  {
    this.registry = registry;
  }

  public void printPerFile()
  {
    for (String file : registry.getIndexedFiles())
    {
      out.println("File: " + file);
      out.println("  imports:");
      for (String _import : registry.getImports(file))
      {
        out.println("\t" + _import);
      }
    }

  }

}
