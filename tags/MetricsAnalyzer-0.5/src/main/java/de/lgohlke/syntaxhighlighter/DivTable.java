package de.lgohlke.syntaxhighlighter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>DivTable class.</p>
 *
 * @author lars
 * @version $Id: $
 */
public class DivTable
{

  private final List<String>  cellList  = new ArrayList<String>();
  private int[]               width;

  private final static String DIV_FIRST = "<div style=\"float:left;width:%width%px\">%content%</div>";
  private final static String DIV_LAST  = "<div style=\"clear:left\"/>";

  /**
   * <p>width.</p>
   *
   * @param width a int.
   * @return a {@link de.lgohlke.syntaxhighlighter.DivTable} object.
   */
  public DivTable width(final int... width)
  {
    this.width = width;
    return this;
  }

  /**
   * <p>cell.</p>
   *
   * @param text a {@link java.lang.String} object.
   * @return a {@link de.lgohlke.syntaxhighlighter.DivTable} object.
   */
  public DivTable cell(final String text)
  {
    this.cellList.add(text);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    StringBuffer b = new StringBuffer();
    if (width != null)
    {
      int width_i = 0;
      for (String content : cellList)
      {
        Integer w;
        if (width.length > width_i)
        {
          w = width[width_i++];
        }
        else
        {
          w = width[0];
        }
        b.append(DIV_FIRST.replace("%width%", w.toString()).replace("%content%", content));
      }
    }
    b.append(DIV_LAST);

    return b.toString();
  }

}
