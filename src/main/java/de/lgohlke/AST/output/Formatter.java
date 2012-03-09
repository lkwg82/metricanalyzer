package de.lgohlke.AST.output;

public class Formatter
{
  private int level = 0;

  public void minus()
  {
    level--;
  }

  public void plus()
  {
    level++;
  }

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
