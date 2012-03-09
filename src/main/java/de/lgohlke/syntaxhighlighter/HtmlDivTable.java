package de.lgohlke.syntaxhighlighter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HtmlDivTable extends StyleableElementAbstract
{

  private static final String TABLE_HEAD   = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" " + STYLE + ">";
  private static final String TABLE_FOOTER = "</table>";

  private final int           rows;
  private final int           columns;
  private final List<String>  cells;

  private String              columnStyle  = "";

  public HtmlDivTable(final int columns, final int rows)
  {
    this.columns = columns;
    this.rows = rows;
    cells = new ArrayList<String>(columns * rows);
  }

  public HtmlDivTable(final int columns)
  {
    this(columns, 0);
  }

  public HtmlDivTable cell(final String string)
  {
    this.cells.add(string);
    return this;
  }

  @Override
  public String toString()
  {
    StringBuffer b = new StringBuffer();

    Queue<String> _cells = new LinkedList<String>(cells);
    b.append("<div class=\"syntaxhighlighter MetricAnalyzer\">");
    // b.append("<div class=\"toolbar\"></div>");
    b.append(styleElement(TABLE_HEAD));
    b.append("\n");
    b.append(getColumnStyle());

    StringBuffer[] columnDivs = new StringBuffer[columns];

    for (int i = 0; i < cells.size(); i++)
    {
      int col = i % columns;
      int row = (int) Math.floor(i / (double) columns);

      if ((row == 0) && (i < columns))// erste Zeile
      {
        columnDivs[col] = new StringBuffer("\t<td class=\" cell\"> \n");
      }

      StringBuffer currentColumn = columnDivs[i % columns];

      currentColumn.append("\t\t");
      if (row == (rows - 1))// last row
      {
        currentColumn.append("<div class=\"head\">");
      }
      else
      {
        currentColumn.append("<div class=\"metric\">");
      }
      currentColumn.append(_cells.poll());
      currentColumn.append("</div>");
      currentColumn.append("\n");
    }

    for (StringBuffer col : columnDivs)
    {
      col.append("\t</td>\n");
      b.append(col);
    }

    b.append(TABLE_FOOTER);
    b.append("</div>");

    return b.toString();
  }

  public String getColumnStyle()
  {
    return columnStyle;
  }

  public void setColumnStyle(final String columnStyle)
  {
    this.columnStyle = columnStyle;
  }
}
