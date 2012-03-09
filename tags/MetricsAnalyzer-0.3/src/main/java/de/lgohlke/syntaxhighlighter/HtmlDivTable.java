package de.lgohlke.syntaxhighlighter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <p>HtmlDivTable class.</p>
 *
 * @author lars
 * @version $Id: $
 */
public class HtmlDivTable extends StyleableElementAbstract
{

  private static final String TABLE_HEAD   = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" " + STYLE + ">";
  private static final String TABLE_FOOTER = "</table>";

  private final int           rows;
  private final int           columns;
  private final List<String>  cells;

  private String              columnStyle  = "";

  /**
   * <p>Constructor for HtmlDivTable.</p>
   *
   * @param columns a int.
   * @param rows a int.
   */
  public HtmlDivTable(final int columns, final int rows)
  {
    this.columns = columns;
    this.rows = rows;
    cells = new ArrayList<String>(columns * rows);
  }

  /**
   * <p>Constructor for HtmlDivTable.</p>
   *
   * @param columns a int.
   */
  public HtmlDivTable(final int columns)
  {
    this(columns, 0);
  }

  /**
   * <p>cell.</p>
   *
   * @param string a {@link java.lang.String} object.
   * @return a {@link de.lgohlke.syntaxhighlighter.HtmlDivTable} object.
   */
  public HtmlDivTable cell(final String string)
  {
    this.cells.add(string);
    return this;
  }

  /** {@inheritDoc} */
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

    List<StringBuffer> columnDivs = new ArrayList<StringBuffer>(columns);

    for (int i = 0; i < cells.size(); i++)
    {
      //      int col = i % columns;
      int row = (int) Math.floor(i / (double) columns);

      if ((row == 0) && (i < columns))// erste Zeile
      {
        columnDivs.add(new StringBuffer("\t<td class=\" cell\"> \n"));
      }

      StringBuffer currentColumn = columnDivs.get(i % columns);

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

  /**
   * <p>Getter for the field <code>columnStyle</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getColumnStyle()
  {
    return columnStyle;
  }

  /**
   * <p>Setter for the field <code>columnStyle</code>.</p>
   *
   * @param columnStyle a {@link java.lang.String} object.
   */
  public void setColumnStyle(final String columnStyle)
  {
    this.columnStyle = columnStyle;
  }
}
