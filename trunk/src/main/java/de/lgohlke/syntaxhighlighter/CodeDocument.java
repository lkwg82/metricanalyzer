package de.lgohlke.syntaxhighlighter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * <p>
 * CodeDocument class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
public class CodeDocument
{
  private final static String header  = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" + //
      "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">" + "<head>\n" + //
      "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + //
      "<title>%title%</title>\n" + //
      "<script type=\"text/javascript\" src=\"%baseDir%/scripts/shCore.js\"></script>\n" + //
      "<script type=\"text/javascript\" src=\"%baseDir%/scripts/shBrushJava.js\"></script>\n" + //
      "<link type=\"text/css\" rel=\"stylesheet\" href=\"%baseDir%/styles/shCoreDefault.css\"/>\n" + //
      "<script type=\"text/javascript\" src=\"%baseDir%/jquery.min.js\"></script>\n" + //
      "<script type=\"text/javascript\" src=\"%baseDir%/jquery.tablesorter.min.js\"></script>\n" + //
      "<script type=\"text/javascript\" src=\"%baseDir%/myscript.js\"></script>\n" + //
      "<script type=\"text/javascript\">SyntaxHighlighter.all();</script>\n" + "<style type=\"text/css\">" + //
      "body { " + //
      " margin: 0;" + //
      " padding: 0;" + //
      "}" + //
      "</style>" + //
      "<link type=\"text/css\" rel=\"stylesheet\" href=\"%baseDir%/mycss.css\"/>\n" + //
      "\n" + "</head>\n" + "\n" + //
      "<body style=\"background: white; font-family: Helvetica\">";
  private String              title   = "no title";
  private String              baseDir = "";
  private final StringBuffer  text    = new StringBuffer();

  /**
   * <p>
   * Constructor for CodeDocument.
   * </p>
   */
  public CodeDocument()
  {
    // ok
  }

  /**
   * simply for wrapping a whole new one
   * 
   * @param document
   *          a {@link de.lgohlke.syntaxhighlighter.CodeDocument} object.
   */
  public CodeDocument(final CodeDocument document)
  {
    this.baseDir = document.baseDir;
    this.title = document.title;
    this.text.append(document.text);
  }

  /**
   * <p>
   * baseDirectory.
   * </p>
   * 
   * @param baseDir
   *          a {@link java.lang.String} object.
   * @return a {@link de.lgohlke.syntaxhighlighter.CodeDocument} object.
   */
  public CodeDocument baseDirectory(final String baseDir)
  {
    this.baseDir = baseDir;
    return this;
  }

  /**
   * <p>
   * title.
   * </p>
   * 
   * @param title
   *          a {@link java.lang.String} object.
   * @return a {@link de.lgohlke.syntaxhighlighter.CodeDocument} object.
   */
  public CodeDocument title(final String title)
  {
    this.title = title;
    return this;
  }

  /**
   * <p>
   * writeToFile.
   * </p>
   * 
   * @param file
   *          a {@link java.io.File} object.
   * @throws java.io.IOException
   *           if any.
   */
  public void writeToFile(final File file) throws IOException
  {
    String headlines = header.replace("%title%", title).replace("%baseDir%", baseDir);
    String tablesorter = "<script type=\"text/javascript\" >$(document).ready(function() {   $(\"#myTable\").tablesorter();  });</script>";
    String footlines = "</body></html>";

    FileUtils.writeStringToFile(file, headlines + text + tablesorter + footlines);

  }

  /**
   * <p>
   * text.
   * </p>
   * 
   * @param text
   *          a {@link java.lang.String} object.
   * @return a {@link de.lgohlke.syntaxhighlighter.CodeDocument} object.
   */
  public CodeDocument text(final String text)
  {
    this.text.append(text);
    return this;
  }

}
