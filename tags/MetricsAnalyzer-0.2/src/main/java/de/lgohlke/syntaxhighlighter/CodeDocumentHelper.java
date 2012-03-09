package de.lgohlke.syntaxhighlighter;

import de.lgohlke.io.bo.TestMethod;

public class CodeDocumentHelper
{

  /**
   * @param test
   * @param highlightLine
   * @param showBeforeAndAfter
   *          lines before and after
   * @return
   */
  public String getFormattedCode(final TestMethod test, final int highlightLine, final int showBeforeAndAfter, final int startLineCount)
  {
    StringBuffer b = new StringBuffer();

    String lines[] = test.getMethod().getSourceCode().split("\\r?\\n");

    int target = highlightLine - test.getMethod().getLineNumber() - 2;
    int start = target - showBeforeAndAfter;
    int end = target + showBeforeAndAfter;

    int preLine = 0;
    int postLine = 0;

    for (int i = start; (i >= 0) && (i < lines.length) && (i < target); i++)
    {
      if ((i != start) && (lines[i].length() != 0))
      {
        preLine++;
        b.append(lines[i]);
      }
      b.append("\n");
    }

    if ((lines.length > target) && (target >= 0))
    {
      b.append(lines[target].length() == 0 ? " " : lines[target]);
      b.append("\n");
    }

    for (int i = target + 1; (i >= 0) && (i < lines.length) && (i <= end); i++)
    {
      b.append(lines[i].length() == 0 ? " " : lines[i]);
      b.append("\n");
      postLine++;
    }

    CodeSnippet snippet = new CodeSnippet(b.toString(), startLineCount);
    snippet.setHighlightLines(highlightLine - (showBeforeAndAfter * 2 - preLine - postLine));

    return snippet.toString();
  }
}
