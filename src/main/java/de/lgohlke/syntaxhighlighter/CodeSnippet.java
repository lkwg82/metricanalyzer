package de.lgohlke.syntaxhighlighter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaSource;

import de.lgohlke.io.bo.TestClass;
import de.lgohlke.io.bo.TestMethod;

/**
 * represents a code snippet wrapped for beeing highlight by syntaxhighlighter
 *
 * @author lars
 * @version $Id: $
 */
public class CodeSnippet
{

  private final String source;
  private final int    firstLine;
  private int[]        highlightLines;

  /**
   * <p>Constructor for CodeSnippet.</p>
   *
   * @param code a {@link java.lang.String} object.
   * @param firstline a int.
   */
  public CodeSnippet(final String code, final int firstline)
  {
    this.source = code;
    this.firstLine = firstline;
  }

  /**
   * <p>Constructor for CodeSnippet.</p>
   *
   * @param code a {@link java.lang.String} object.
   */
  public CodeSnippet(final String code)
  {
    this(code, 1);
  }

  /**
   * <p>Constructor for CodeSnippet.</p>
   *
   * @param source a {@link com.thoughtworks.qdox.model.JavaSource} object.
   */
  public CodeSnippet(final JavaSource source)
  {
    this(source.getCodeBlock());
  }

  /**
   * <p>Constructor for CodeSnippet.</p>
   *
   * @param clazz a {@link de.lgohlke.io.bo.TestClass} object.
   */
  public CodeSnippet(final TestClass clazz)
  {
    this(clazz.getClazz().getSource());
  }

  /**
   * <p>Constructor for CodeSnippet.</p>
   *
   * @param test a {@link de.lgohlke.io.bo.TestMethod} object.
   * @param firstline a int.
   */
  public CodeSnippet(final TestMethod test, final int firstline)
  {
    this(test.getMethod().getSourceCode(), firstline);
  }

  /**
   * <p>Constructor for CodeSnippet.</p>
   *
   * @param test a {@link de.lgohlke.io.bo.TestMethod} object.
   */
  public CodeSnippet(final TestMethod test)
  {
    this(test.getMethod());
  }

  /**
   * <p>Constructor for CodeSnippet.</p>
   *
   * @param method a {@link com.thoughtworks.qdox.model.JavaMethod} object.
   */
  public CodeSnippet(final JavaMethod method)
  {
    firstLine = method.getLineNumber() + 1;

    StringBuffer b = new StringBuffer();

    for (Annotation a : method.getAnnotations())
    {
      b.append('@');
      b.append(a.getType().getFullyQualifiedName());
      b.append('\n');
    }

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
  /** {@inheritDoc} */
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
   * <p>Setter for the field <code>highlightLines</code>.</p>
   *
   * @param highlightLines a int.
   */
  public void setHighlightLines(final int... highlightLines)
  {
    this.highlightLines = highlightLines;
  }

  /**
   * <p>Getter for the field <code>highlightLines</code>.</p>
   *
   * @return an array of int.
   */
  public int[] getHighlightLines()
  {
    return highlightLines.clone();
  }
}
