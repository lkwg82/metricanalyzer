package de.lgohlke.AST.visitors;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.sonar.java.ast.visitor.AstUtils;
import org.sonar.java.ast.visitor.JavaAstVisitor;
import org.sonar.squid.api.SourceCode;
import org.sonar.squid.measures.MetricDef;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import de.lgohlke.AST.ASTMetrics;
import de.lgohlke.AST.AstHelper;
import de.lgohlke.AST.Registry;
import de.lgohlke.AST.VisitorKey;
import de.lgohlke.AST.calculators.DISTANCE_RULE;
import de.lgohlke.AST.calculators.TypeDistanceCalculator;

/**
 * Visitor for Method- and Variable-definitions
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
public class VariableTypeDistanceVisitor extends JavaAstVisitor
{
  /** Constant <code>WANTED_TOKENS</code> */
  public static final List<Integer>  WANTED_TOKENS     = Arrays.asList(TokenTypes.VARIABLE_DEF, TokenTypes.METHOD_DEF);
  private AstHelper                  astHelper         = null;
  private String                     key;
  private final Registry             registry;
  private final JavaAstVisitorHelper visitorHelper;

  private final DISTANCE_RULE        rule;

  private List<DetailAST>            classVariablesDef = new ArrayList<DetailAST>();

  // private Set<String> files = new TreeSet<String>();
  // private int wastedFiles = 0;
  // private long wastedChars = 0L;
  /**
   * <p>
   * Constructor for VariableTypeDistanceVisitor.
   * </p>
   * 
   * @param registry
   *          a {@link de.lgohlke.AST.Registry} object.
   * @param rule
   *          a {@link de.lgohlke.AST.calculators.DISTANCE_RULE} object.
   */
  public VariableTypeDistanceVisitor(final Registry registry, final DISTANCE_RULE rule)
  {
    this.registry = registry;
    this.rule = rule;
    visitorHelper = new JavaAstVisitorHelper(this);
  }

  /** {@inheritDoc} */
  @Override
  public List<Integer> getWantedTokens()
  {
    return WANTED_TOKENS;
  }

  /** {@inheritDoc} */
  @Override
  public void leaveToken(final DetailAST ast)
  {
    SourceCode res = peekSourceCode();

    VisitorKey visitorKey = visitorHelper.getVisitorKey(ast, res);

    // TODO only put usefull lines, for each line the whole file?
    registry.getCode().put(visitorKey, getFileContents().getLines());
    // if ( !files.add(key)){
    // wastedFiles++;
    // for(String line : getFileContents().getLines()){
    // wastedChars += line.length();
    // }
    // log.debug("wasted files " + wastedFiles + " wasted chars " + wastedChars);
    // }

    try
    {
      if (ast.getType() == TokenTypes.VARIABLE_DEF)
      {
        handleVarDefs(ast, res, visitorKey, false);
      }
      else
      {
        //        log.debug("handling class vars " + res.getKey());
        for (DetailAST classVarDefAst : classVariablesDef)
        {
          // log.debug("call recursive for " + classVarDefAst);
          handleVarDefs(classVarDefAst, res, visitorKey, true);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      visitorHelper.showInfo(ast, res);
    }

  }

  private void handleVarDefs(final DetailAST ast, final SourceCode res, final VisitorKey visitorKey, final boolean isclassVarDef)
  {
    MetricDef AST_VARIABLE_DEFINITION_TYPE_DISTANCE;
    MetricDef AST_NUMBER_OF_VARIABLE_DEFINITION;
    MetricDef AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE;
    MetricDef AST_VARIABLE_DEFINITION_TYPE_DISTANCE_LIST;

    if (isclassVarDef)
    {
      AST_VARIABLE_DEFINITION_TYPE_DISTANCE = ASTMetrics.AST_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE;
      AST_VARIABLE_DEFINITION_TYPE_DISTANCE_LIST = ASTMetrics.AST_CLASS_VARIABLE_DEFINITION_TYPE_DISTANCE_LIST;
      AST_NUMBER_OF_VARIABLE_DEFINITION = ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION;
      AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE = ASTMetrics.AST_CLASS_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE;
    }
    else
    {
      AST_VARIABLE_DEFINITION_TYPE_DISTANCE = ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE;
      AST_VARIABLE_DEFINITION_TYPE_DISTANCE_LIST = ASTMetrics.AST_VARIABLE_DEFINITION_TYPE_DISTANCE_LIST;
      AST_NUMBER_OF_VARIABLE_DEFINITION = ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION;
      AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE = ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE;

      DetailAST methodDefAst = AstUtils.findParent(ast, TokenTypes.METHOD_DEF);

      // class variables
      if (methodDefAst == null)
      {
        classVariablesDef.add(ast);
      }
    }

    String currentClass = astHelper.getCurrentClass(ast);
    if (currentClass.length() == 0)
    {
      log.error("qdox parser bug : " + key);
    }
    else
    {
      Set<DetailAST> astTypeSet = astHelper.findGenericTypes(ast);

      List<String> canonicalClassnames = new ArrayList<String>(astTypeSet.size());
      for (DetailAST astType : astTypeSet)
      {
        String canonicalType = registry.getCanonicalClassnameOfType(key, astType.getText());
        canonicalClassnames.add(canonicalType);
      }
      String typeName = rule.chooseAst(currentClass, canonicalClassnames);

      Double metricDistance = 0D;
      if (typeName != null)
      {
        String canonicalType = registry.getCanonicalClassnameOfType(key, typeName);

        TypeDistanceCalculator calculator = new TypeDistanceCalculator(currentClass, canonicalType);
        metricDistance = calculator.calculate();

        registry.addDescription(visitorKey, AST_VARIABLE_DEFINITION_TYPE_DISTANCE, calculator.describe());

        res.add(AST_VARIABLE_DEFINITION_TYPE_DISTANCE, metricDistance);
        registry.addMetricPerLine(visitorKey, AST_VARIABLE_DEFINITION_TYPE_DISTANCE, metricDistance);
      }

      // useless comparision
      // if (ast.getType() == TokenTypes.VARIABLE_DEF)
      {
        res.add(AST_NUMBER_OF_VARIABLE_DEFINITION, 1);
        registry.addMetricPerLine(visitorKey, AST_NUMBER_OF_VARIABLE_DEFINITION, 1D);

        /**
         * retrieve the old list and add one and set again
         */
        @SuppressWarnings("unchecked")
        List<Double> typeDistanceList = (List<Double>) res.getData(AST_VARIABLE_DEFINITION_TYPE_DISTANCE_LIST);
        if (typeDistanceList == null)
        {
          typeDistanceList = new ArrayList<Double>();
        }

        typeDistanceList.add(metricDistance);

        res.addData(AST_VARIABLE_DEFINITION_TYPE_DISTANCE_LIST, typeDistanceList);

        if (metricDistance > 0)
        {
          res.add(AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE, 1);
          registry.addMetricPerLine(visitorKey, AST_NUMBER_OF_VARIABLE_DEFINITION_NON_ZERO_TYPE_DISTANCE, 1D);
        }
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void visitFile(final DetailAST ast)
  {
    File file = new File(getFileContents().getFilename());
    key = file.getAbsolutePath();
    astHelper = new AstHelper(registry, key, this);
    visitorHelper.setAsthelper(astHelper);
  }

  @Override
  public void leaveFile(final DetailAST ast)
  {
    classVariablesDef.clear();
  }
}
