package de.lgohlke.AST.visitors;

import java.io.PrintStream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.sonar.java.ast.visitor.JavaAstVisitor;
import org.sonar.squid.api.SourceCode;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import de.lgohlke.AST.AstHelper;
import de.lgohlke.AST.VisitorKey;

/**
 * <p>
 * JavaAstVisitorHelper class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@RequiredArgsConstructor
public class JavaAstVisitorHelper
{
  private final PrintStream    out = System.out;
  private final JavaAstVisitor visitor;
  @Setter
  @Getter
  private AstHelper            asthelper;

  /**
   * <p>
   * getCode.
   * </p>
   * 
   * @param res
   *          a {@link org.sonar.squid.api.SourceCode} object.
   * @return a {@link java.lang.String} object.
   */
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

  /**
   * <p>
   * getVisitorKey.
   * </p>
   * 
   * @param ast
   *          a {@link com.puppycrawl.tools.checkstyle.api.DetailAST} object.
   * @param res
   *          a {@link org.sonar.squid.api.SourceCode} object.
   * @return a {@link de.lgohlke.AST.VisitorKey} object.
   */
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
        case TokenTypes.ASSIGN:
          ident = "=";
          key.setStartLine(ast.getLineNo());
          break;
        default:
          ident = "not handled for " + key.getAstTokenString();
          break;
      }

      key.setIdentifier(ident);
    }

    return key;
  }

  /**
   * <p>
   * showInfo.
   * </p>
   * 
   * @param ast
   *          a {@link com.puppycrawl.tools.checkstyle.api.DetailAST} object.
   * @param res
   *          a {@link org.sonar.squid.api.SourceCode} object.
   */
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
}
