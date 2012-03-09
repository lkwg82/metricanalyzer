package de.lgohlke.AST.visitors;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.sonar.java.ast.visitor.JavaAstVisitor;
import org.sonar.squid.api.SourceCode;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import de.lgohlke.AST.ASTMetrics;
import de.lgohlke.AST.AstHelper;
import de.lgohlke.AST.Registry;
import de.lgohlke.AST.VisitorKey;

/**
 * <p>
 * MethodVisitor class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
public class AssertCountVisitor extends JavaAstVisitor
{
  public static final List<Integer> WANTED_TOKENS = Arrays.asList(TokenTypes.METHOD_CALL);
  private final Registry            registry;
  private AstHelper                 astHelper     = null;
  private String                    keyFile;
  private JavaAstVisitorHelper      visitorHelper;

  public AssertCountVisitor(final Registry registry)
  {

    this.registry = registry;
    visitorHelper = new JavaAstVisitorHelper(this);
  }

  /*
   * (non-Javadoc)
   * @see org.sonar.java.ast.visitor.JavaAstVisitor#getWantedTokens()
   */
  @Override
  public List<Integer> getWantedTokens()
  {
    return WANTED_TOKENS;
  }

  /*
   * (non-Javadoc)
   * @see org.sonar.java.ast.visitor.JavaAstVisitor#visitToken(com.puppycrawl.tools.checkstyle.api.DetailAST)
   */
  @Override
  public void visitToken(final DetailAST ast)
  {
    SourceCode res = peekSourceCode();

    VisitorKey visitorKey = visitorHelper.getVisitorKey(ast, res);

    List<String> typeList = new ArrayList<String>();
    if ((ast.getFirstChild() != null) && (ast.getFirstChild().getType() == TokenTypes.DOT))
    {
      astHelper.findCompleteDottedType(ast.getFirstChild(), typeList);
    }
    else
    {
      typeList.add(ast.getText());
    }
    // String methodName = StringUtils.join(typeList,".");
    if (typeList.size() > 0)
    {
      String lastName = typeList.get(typeList.size() - 1);

      if (lastName.toLowerCase().contains("assert"))
      {
        ASTMetrics metric = ASTMetrics.AST_NUMBER_OF_ASSERT_STATEMENTS;
        res.add(metric, 1);
        registry.addMetricPerLine(visitorKey, metric, 1D);
        registry.addDescription(visitorKey, metric, "found an assert-method-call");
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see org.sonar.java.ast.visitor.JavaAstVisitor#leaveToken(com.puppycrawl.tools.checkstyle.api.DetailAST)
   */
  @Override
  public void leaveToken(final DetailAST ast)
  {

  }

  /*
   * (non-Javadoc)
   * @see org.sonar.java.ast.visitor.JavaAstVisitor#visitFile(com.puppycrawl.tools.checkstyle.api.DetailAST)
   */
  @Override
  public void visitFile(final DetailAST ast)
  {
    File file = new File(getFileContents().getFilename());
    keyFile = file.getAbsolutePath();
    astHelper = new AstHelper(registry, keyFile, this);
    visitorHelper.setAsthelper(astHelper);
  }
}
