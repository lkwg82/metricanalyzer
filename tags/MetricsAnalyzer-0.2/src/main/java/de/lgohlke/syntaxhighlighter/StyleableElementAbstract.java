package de.lgohlke.syntaxhighlighter;

public abstract class StyleableElementAbstract
{
  protected static final String STYLE = "%STYLE%";
  private String                style = "";

  public void setStyle(final String style)
  {
    this.style = style;
  }

  public String getStyle()
  {
    return style;
  }

  protected String styleElement(final String element)
  {
    return element.replace(STYLE, style == null ? "" : style.length() == 0 ? "" : "style=\"" + style + "\"");
  }
}
