package de.lgohlke.syntaxhighlighter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <p>HtmlTable class.</p>
 *
 * @author lars
 * @version $Id: $
 */
public class HtmlTable extends StyleableElementAbstract
{

  private static final String TABLE_HEAD   = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" " + STYLE + ">";
  private static final String TABLE_FOOTER = "</table>";
  private static final String ROW_START    = "<tr id=\"row_%d\">";
  private static final String ROW_END      = "</tr>";

  private final int           columns;
  private final List<String>  cells;
  private final List<String>  cellStyles;
  private String              columnStyle;

  /**
   * <p>Constructor for HtmlTable.</p>
   *
   * @param columns a int.
   */
  public HtmlTable(final int columns)
  {
    this(columns, 0);
  }

  /**
   * <p>Constructor for HtmlTable.</p>
   *
   * @param columns a int.
   * @param rows a int.
   */
  public HtmlTable(final int columns, final int rows)
  {
    this.columns = columns;
    cells = new ArrayList<String>(columns * rows);
    cellStyles = new ArrayList<String>(cells.size());
  }

  /**
   * <p>cell.</p>
   *
   * @param string a {@link java.lang.String} object.
   * @param style a {@link java.lang.String} object.
   * @return a {@link de.lgohlke.syntaxhighlighter.HtmlTable} object.
   */
  public HtmlTable cell(final String string, final String style)
  {
    this.cells.add(string);
    cellStyles.add(style);
    return this;
  }

  /**
   * <p>cell.</p>
   *
   * @param string a {@link java.lang.String} object.
   * @return a {@link de.lgohlke.syntaxhighlighter.HtmlTable} object.
   */
  public HtmlTable cell(final String string)
  {
    return cell(string, null);
  }

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    StringBuffer b = new StringBuffer();

    Queue<String> _cells = new LinkedList<String>(cells);
    Queue<String> _styles = new LinkedList<String>(cellStyles);

    b.append(styleElement(TABLE_HEAD));
    b.append(getColumnStyle() == null ? "" : getColumnStyle());
    b.append("\n");

    b.append("<tbody>");
    b.append("\n");
    for (int i = 0; i < cells.size(); i++)
    {
      int col = i % columns;
      int row = (int) Math.floor(i / (double) columns);
      if (col == 0)
      {
        handleRowStart(b, row);
      }
      b.append("\t\t");
      b.append("<td ");

      handleCell(b, _cells.poll(), _styles.poll(), col, row);

      if (i % columns == columns - 1)
      {
        handleRowEnd(b, row);
      }
    }
    b.append("</tbody>");
    b.append("\n");
    b.append(TABLE_FOOTER);

    return b.toString();
  }

  /**
   * <p>handleRowEnd.</p>
   *
   * @param b a {@link java.lang.StringBuffer} object.
   * @param row a int.
   */
  protected void handleRowEnd(final StringBuffer b, final int row)
  {
    b.append("\t");
    b.append(ROW_END);
    b.append("\n");
  }

  /**
   * <p>handleCell.</p>
   *
   * @param b a {@link java.lang.StringBuffer} object.
   * @param content a {@link java.lang.String} object.
   * @param style a {@link java.lang.String} object.
   * @param col a int.
   * @param row a int.
   */
  protected void handleCell(final StringBuffer b, final String content, final String style, final int col, final int row)
  {
    if ((style != null) && (style.length() > 0))
    {
      b.append(" style=\"");
      b.append(style);
      b.append("\" ");
    }

    b.append(" class=\".gutter .line\">");
    b.append(content);
    b.append("</td>");
    b.append("\n");

  }

  /**
   * <p>handleRowStart.</p>
   *
   * @param b a {@link java.lang.StringBuffer} object.
   * @param row a int.
   */
  protected void handleRowStart(final StringBuffer b, final int row)
  {
    b.append("\t");
    b.append(String.format(ROW_START, row));
    b.append("\n");
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
