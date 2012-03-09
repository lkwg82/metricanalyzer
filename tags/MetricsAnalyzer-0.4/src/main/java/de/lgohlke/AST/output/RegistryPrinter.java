package de.lgohlke.AST.output;

import java.io.PrintStream;

import de.lgohlke.AST.Registry;

/**
 * <p>RegistryPrinter class.</p>
 *
 * @author lars
 * @version $Id: $
 */
public class RegistryPrinter
{
  private final PrintStream out = System.out;
  private final Registry    registry;

  /**
   * <p>Constructor for RegistryPrinter.</p>
   *
   * @param registry a {@link de.lgohlke.AST.Registry} object.
   */
  public RegistryPrinter(final Registry registry)
  {
    this.registry = registry;
  }

  /**
   * <p>printPerFile.</p>
   */
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
