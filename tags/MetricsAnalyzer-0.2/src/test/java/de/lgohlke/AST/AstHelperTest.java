package de.lgohlke.AST;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sonar.java.ast.JavaAstScanner;
import org.sonar.java.squid.JavaSquidConfiguration;
import org.sonar.squid.Squid;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import de.lgohlke.AST.calculators.DISTANCE_RULE;
import de.lgohlke.AST.visitors.JavaAstVisitorHelper;
import de.lgohlke.AST.visitors.QDoxFileVisitor;
import de.lgohlke.AST.visitors.VariableTypeDistanceVisitor;

public class AstHelperTest
{

  private Squid                         squid;
  private MyVariableTypeDistanceVisitor visitor;

  @Before
  public void setup()
  {

    Registry registry = new Registry();
    squid = new Squid(new JavaSquidConfiguration(false));
    visitor = new MyVariableTypeDistanceVisitor(registry, DISTANCE_RULE.FIRST);

    squid.registerVisitor(new QDoxFileVisitor(registry));
    squid.registerVisitor(visitor);
  }

  @Test
  public void findTypeWithCanonicalName()
  {
    squid.register(JavaAstScanner.class).scanFile(new File("src/test/resources/AstHelperTest/StaticInnerClass.java"));

    Assert.assertTrue("eigentlich sollte StaticInnerClass.Metric.LEVEL enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"), visitor
        .getTypes().contains("StaticInnerClass.Metric.LEVEL"));
  }

  @Test
  public void findTypeTwoDimensionalArray()
  {
    squid.register(JavaAstScanner.class).scanFile(new File("src/test/resources/AstHelperTest/TwoDimensionalArray.java"));

    Assert.assertTrue("eigentlich sollte Object enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"),
        visitor.getTypes().contains("Object"));

    Assert
        .assertTrue("eigentlich sollte int enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"), visitor.getTypes().contains("int"));
  }

  @Test
  public void findTypeInGenericList()
  {
    squid.register(JavaAstScanner.class).scanFile(new File("src/test/resources/AstHelperTest/Generics.java"));

    Assert.assertTrue("eigentlich sollte List enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"),
        visitor.getTypes().contains("List"));
    Assert
        .assertTrue("eigentlich sollte Map enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"), visitor.getTypes().contains("Map"));
    Assert.assertTrue("eigentlich sollte String enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"),
        visitor.getTypes().contains("String"));

  }
}

class MyVariableTypeDistanceVisitor extends VariableTypeDistanceVisitor
{

  private String                     key;
  private AstHelper                  astHelper;
  private final Registry             registry;
  private final JavaAstVisitorHelper visitorHelper;

  private final List<String>         types = new ArrayList<String>();

  public MyVariableTypeDistanceVisitor(final Registry registry, final DISTANCE_RULE rule)
  {
    super(registry, rule);
    this.registry = registry;
    this.visitorHelper = new JavaAstVisitorHelper(this);
  }

  @Override
  public void leaveToken(final DetailAST ast)
  {

    // String name = ast.findFirstToken(TokenTypes.IDENT).getText();
    // SourceCode res = peekSourceCode();

    // VisitorKey visitorKey = visitorHelper.getVisitorKey(ast, res);

    // registry.getCode().put(visitorKey, getFileContents().getLines());

    // visitorHelper.showInfo(ast, res);

    // String currentClass = astHelper.getCurrentClass(ast);
    Set<DetailAST> astTypes = astHelper.findGenericTypes(ast);
    // DetailAST astType = astHelper.findType(ast);

    // String typeName = astType.getText();

    for (DetailAST detailAst : astTypes)
    {
      // System.out.println(detailAst.getText());
      getTypes().add(detailAst.getText());
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

  public List<String> getTypes()
  {
    return types;
  }
}
