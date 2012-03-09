package de.lgohlke.syntaxhighlighter;

/**
 * <p>Abstract StyleableElementAbstract class.</p>
 *
 * @author lars
 * @version $Id: $
 */
public abstract class StyleableElementAbstract
{
  /** Constant <code>STYLE="%STYLE%"</code> */
  protected static final String STYLE = "%STYLE%";
  private String                style = "";

  /**
   * <p>Setter for the field <code>style</code>.</p>
   *
   * @param style a {@link java.lang.String} object.
   */
  public void setStyle(final String style)
  {
    this.style = style;
  }

  /**
   * <p>Getter for the field <code>style</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getStyle()
  {
    return style;
  }

  /**
   * <p>styleElement.</p>
   *
   * @param element a {@link java.lang.String} object.
   * @return a {@link java.lang.String} object.
   */
  protected String styleElement(final String element)
  {
    return element.replace(STYLE, style == null ? "" : style.length() == 0 ? "" : "style=\"" + style + "\"");
  }
}
