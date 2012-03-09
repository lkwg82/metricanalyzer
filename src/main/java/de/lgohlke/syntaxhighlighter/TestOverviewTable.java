package de.lgohlke.syntaxhighlighter;

/**
 * <p>TestOverviewTable class.</p>
 *
 * @author lars
 * @version $Id: $
 */
public class TestOverviewTable extends HtmlTable
{

  /**
   * <p>Constructor for TestOverviewTable.</p>
   *
   * @param columns a int.
   */
  public TestOverviewTable(final int columns)
  {
    super(columns);
  }

  /**
   * <p>Constructor for TestOverviewTable.</p>
   *
   * @param columns a int.
   * @param rows a int.
   */
  public TestOverviewTable(final int columns, final int rows)
  {
    super(columns, rows);
  }

  /** {@inheritDoc} */
  @Override
  protected void handleRowStart(final StringBuffer b, final int row)
  {
    b.append("\t");
    b.append(String.format("<tr id=\"row_%d\" style=\"%s\">", row, row == 0 ? "height:400px;vertical-align:bottom;" : ""));
    b.append("\n");
  }

  /** {@inheritDoc} */
  @Override
  protected void handleCell(final StringBuffer b, final String content, final String style, final int col, final int row)
  {
    if ((style != null) && (style.length() > 0))
    {
      b.append(" style=\"");
      b.append(style);
      b.append("\" ");
    }

    b.append(String.format(" class=\"col-%d\">", col));
    b.append(content);
    b.append("</td>");
    b.append("\n");

  }

}
