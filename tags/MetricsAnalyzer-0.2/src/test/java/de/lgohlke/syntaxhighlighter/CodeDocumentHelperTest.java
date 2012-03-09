package de.lgohlke.syntaxhighlighter;

import junit.framework.Assert;

import org.junit.Test;

import com.thoughtworks.qdox.model.JavaMethod;

import de.lgohlke.io.bo.TestMethod;

public class CodeDocumentHelperTest
{

  @Test
  public void testGetFormattedCode()
  {
    String sourceCode = "public HtmlDivTable(final int columns, final int rows) \n" + //
        "{\n" + //
        " this.columns = columns;\n" + //
        "this.rows = rows;\n" + //
        "cells = new ArrayList<String>(columns * rows);\n" + //
        "}\n" + //
        "";

    JavaMethod method = new JavaMethod();

    method.setSourceCode(sourceCode);
    TestMethod test = new TestMethod(method);

    {
      int startLineCount = 1;
      int showLine = 5;
      int showLinesBeforeAndAfter = 1;
      String formattedCode = new CodeDocumentHelper().getFormattedCode(test, showLine, showLinesBeforeAndAfter, startLineCount);

      // System.out.println(formattedCode);
      Assert.assertEquals(1 + 2 + showLinesBeforeAndAfter * 2, formattedCode.split("\\r?\\n").length);
    }
  }

}
