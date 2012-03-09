package de.lgohlke.syntaxhighlighter;

import java.util.ArrayList;
import java.util.List;

public class DivTable
{

  private final List<String>  cellList  = new ArrayList<String>();
  private int[]               width;

  private final static String DIV_FIRST = "<div style=\"float:left;width:%width%px\">%content%</div>";
  private final static String DIV_LAST  = "<div style=\"clear:left\"/>";

  public DivTable width(final int... width)
  {
    this.width = width;
    return this;
  }

  public DivTable cell(final String text)
  {
    this.cellList.add(text);
    return this;
  }

  @Override
  public String toString()
  {
    StringBuffer b = new StringBuffer();
    if (width != null)
    {
      int width_i = 0;
      for (String content : cellList)
      {
        int w;
        if (width.length > width_i)
        {
          w = width[width_i++];
        }
        else
        {
          w = width[0];
        }
        b.append(DIV_FIRST.replace("%width%", w + "").replace("%content%", content));
      }
    }
    b.append(DIV_LAST);

    return b.toString();
  }

}
