package de.lgohlke.AST.visitors;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.java.ast.visitor.JavaAstVisitor;
import org.sonar.squid.api.SourceCode;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import de.lgohlke.AST.ASTMetrics;
import de.lgohlke.AST.AstHelper;
import de.lgohlke.AST.Registry;
import de.lgohlke.AST.calculators.DISTANCE_RULE;
import de.lgohlke.AST.calculators.TypeDistanceCalculator;

/**
 * Visitor for Method- and Variable-definitions
 * 
 * @author lars
 */
public class VariableTypeDistanceVisitor extends JavaAstVisitor
{
  private static final Logger        log           = LoggerFactory.getLogger(VariableTypeDistanceVisitor.class);
  public static final List<Integer>  WANTED_TOKENS = Arrays.asList(TokenTypes.VARIABLE_DEF, TokenTypes.METHOD_DEF);
  private AstHelper                  astHelper     = null;
  private String                     key;
  private final Registry             registry;
  private final JavaAstVisitorHelper visitorHelper;

  private final DISTANCE_RULE        rule;

  public VariableTypeDistanceVisitor(final Registry registry, final DISTANCE_RULE rule)
  {
    this.registry = registry;
    this.rule = rule;
    visitorHelper = new JavaAstVisitorHelper(this);
  }

  @Override
  public List<Integer> getWantedTokens()
  {
    return WANTED_TOKENS;
  }

  @Override
  public void leaveToken(final DetailAST ast)
  {
    // String name = ast.findFirstToken(TokenTypes.IDENT).getText();
    SourceCode res = peekSourceCode();

    VisitorKey visitorKey = visitorHelper.getVisitorKey(ast, res);

    registry.getCode().put(visitorKey, getFileContents().getLines());

    // visitorHelper.showInfo(ast, res);

    if (astHelper == null)
    {
      log.error("astHelper should not be null");
    }

    String currentClass = astHelper.getCurrentClass(ast);
    Set<DetailAST> astTypeSet = astHelper.findGenericTypes(ast);

    List<String> canonicalClassnames = new ArrayList<String>();
    for (DetailAST astType : astTypeSet)
    {
      String canonicalType = registry.getCanonicalClassnameOfType(key, astType.getText());
      canonicalClassnames.add(canonicalType);
    }
    String typeName = rule.chooseAst(currentClass, canonicalClassnames);

    if (typeName != null)
    {
      String canonicalType = registry.getCanonicalClassnameOfType(key, typeName);
      Double metricDistance;

      {
        TypeDistanceCalculator calculator = new TypeDistanceCalculator(currentClass, canonicalType);
        metricDistance = calculator.calculate();
        registry.addDescription(visitorKey, ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE, calculator.describe());
      }

      // System.out.println(metricDistance + " # " + visitorKey);
      res.add(ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE, metricDistance);
      registry.addMetricPerLine(visitorKey, ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE, metricDistance);
    }

    if (ast.getType() == TokenTypes.VARIABLE_DEF)
    {
      res.add(ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION, 1);
      registry.addMetricPerLine(visitorKey, ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION, 1D);
    }

  }

  @Override
  public void visitFile(final DetailAST ast)
  {
    File file = new File(getFileContents().getFilename());
    key = file.getAbsolutePath();
    astHelper = new AstHelper(registry, key, this);
    visitorHelper.setAsthelper(astHelper);
  }
}
