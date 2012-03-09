package de.lgohlke.syntaxhighlighter;

public class TestOverviewTable extends HtmlTable
{

  public TestOverviewTable(final int columns)
  {
    super(columns);
  }

  public TestOverviewTable(final int columns, final int rows)
  {
    super(columns, rows);
  }

  @Override
  protected void handleRowStart(final StringBuffer b, final int row)
  {
    b.append("\t");
    b.append(String.format("<tr id=\"row_%d\" style=\"%s\">", row, row == 0 ? "height:400px;vertical-align:bottom;" : ""));
    b.append("\n");
  }

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
