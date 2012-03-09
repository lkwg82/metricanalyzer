package de.lgohlke.AST.output;

/**
 * <p>Formatter class.</p>
 *
 * @author lars
 * @version $Id: $
 */
public class Formatter
{
  private int level = 0;

  /**
   * <p>minus.</p>
   */
  public void minus()
  {
    level--;
  }

  /**
   * <p>plus.</p>
   */
  public void plus()
  {
    level++;
  }

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    StringBuffer spaces = new StringBuffer();
    for (int i = 0; i < level; i++)
    {
      spaces.append(" ");
    }
    return spaces.toString();
  }
}
