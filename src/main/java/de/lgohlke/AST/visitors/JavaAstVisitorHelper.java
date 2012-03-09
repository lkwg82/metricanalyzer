package de.lgohlke.AST.visitors;

import java.io.PrintStream;

import org.sonar.java.ast.visitor.JavaAstVisitor;
import org.sonar.squid.api.SourceCode;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import de.lgohlke.AST.AstHelper;

public class JavaAstVisitorHelper
{
  private final PrintStream    out = System.out;
  private final JavaAstVisitor visitor;
  private AstHelper            asthelper;

  public JavaAstVisitorHelper(final JavaAstVisitor visitor)
  {
    this.visitor = visitor;
  }

  public String getCode(final SourceCode res)
  {
    StringBuffer b = new StringBuffer();
    String[] lines = visitor.getFileContents().getLines();

    int width = String.valueOf(lines.length + 1).length();
    String format = "%" + width + "d %s\n";
    for (int i = 0; i < lines.length; i++)
    {
      b.append(String.format(format, (i + 1), lines[i]));
    }

    return b.toString();
  }

  public VisitorKey getVisitorKey(final DetailAST ast, final SourceCode res)
  {
    VisitorKey key = new VisitorKey();

    key.setFilename(visitor.getFileContents().getFilename());
    key.setStartLine(ast.getLineNo());
    key.setEndLine(res.getEndAtLine());
    key.setAstTokenString(ast.getText());
    key.setAstToken(ast.getType());

    { // find identifier
      String ident = "";
      switch (ast.getType())
      {
        case TokenTypes.METHOD_DEF:
          ident = ast.findFirstToken(TokenTypes.IDENT).getText();
          break;
        case TokenTypes.VARIABLE_DEF:
          ident = ast.findFirstToken(TokenTypes.IDENT).getText();
          key.setEndLine(ast.getLineNo());
          break;
        default:
          ident = "not handled for " + key.getAstTokenString();
          break;
      }

      key.setIdentifier(ident);
    }

    return key;
  }

  public void showInfo(final DetailAST ast, final SourceCode res)
  {
    VisitorKey key = getVisitorKey(ast, res);
    out.println(ast.getText());
    out.println("KEY  : " + res.getKey());
    out.println("File : " + visitor.getFileContents().getFilename());
    out.println(" 1.LN: " + key.getStartLine());
    out.println(" 2.LN: " + key.getEndLine());
    out.println(" ident " + key.getIdentifier());

    out.println("VKey : " + getVisitorKey(ast, res));
    String code = getCode(res);
    out.println("--------------------------------");
    out.println(code);
    out.println("--------------------------------");

  }

  public void setAsthelper(final AstHelper asthelper)
  {
    this.asthelper = asthelper;
  }

  public AstHelper getAsthelper()
  {
    return asthelper;
  }
}
