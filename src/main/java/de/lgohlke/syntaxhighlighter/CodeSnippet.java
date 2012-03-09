package de.lgohlke.syntaxhighlighter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaSource;

import de.lgohlke.io.bo.TestClass;
import de.lgohlke.io.bo.TestMethod;

/**
 * represents a code snippet wrapped for beeing highlight by syntaxhighlighter
 * 
 * @author lars
 */
public class CodeSnippet
{

  private final String source;
  private final int    firstLine;
  private int[]        highlightLines;

  /**
   * @param code
   * @param firstline
   */
  public CodeSnippet(final String code, final int firstline)
  {
    this.source = code;
    this.firstLine = firstline;
  }

  /**
   * @param code
   */
  public CodeSnippet(final String code)
  {
    this(code, 1);
  }

  /**
   * @param source
   */
  public CodeSnippet(final JavaSource source)
  {
    this(source.getCodeBlock());
  }

  /**
   * @param clazz
   */
  public CodeSnippet(final TestClass clazz)
  {
    this(clazz.getClazz().getSource());
  }

  /**
   * @param test
   * @param firstline
   */
  public CodeSnippet(final TestMethod test, final int firstline)
  {
    this(test.getMethod().getSourceCode(), firstline);
  }

  /**
   * @param test
   */
  public CodeSnippet(final TestMethod test)
  {
    JavaMethod method = test.getMethod();
    firstLine = test.getMethod().getLineNumber() + 1;
    StringBuffer b = new StringBuffer();
    b.append(method.getDeclarationSignature(true));
    b.append("{\n");
    b.append(method.getSourceCode());
    b.append("\n}");

    source = b.toString();
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    StringBuffer b = new StringBuffer();

    String highlight = "";
    if (highlightLines != null)
    {
      List<String> highlights = new ArrayList<String>(highlightLines.length);
      for (int i : highlightLines)
      {
        highlights.add(String.valueOf(i));
      }
      highlight = "highlight :[" + StringUtils.join(highlights.toArray(new String[highlights.size()]), ",") + "];";
    }

    b.append("<pre class=\"brush: java;toolbar: false; ");
    b.append("first-line:");
    b.append(firstLine);
    b.append(";");
    b.append(highlight);
    b.append("\">\n");
    b.append(source);
    b.append("</pre>");

    return b.toString();
  }

  /**
   * @param highlightLines
   */
  public void setHighlightLines(final int... highlightLines)
  {
    this.highlightLines = highlightLines;
  }

  /**
   * @return
   */
  public int[] getHighlightLines()
  {
    return highlightLines.clone();
  }
}
