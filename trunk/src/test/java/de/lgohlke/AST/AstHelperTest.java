package de.lgohlke.AST;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sonar.api.resources.InputFile;
import org.sonar.api.resources.InputFileUtils;
import org.sonar.java.ast.JavaAstScanner;
import org.sonar.java.ast.visitor.JavaAstVisitor;
import org.sonar.java.squid.JavaSquidConfiguration;
import org.sonar.squid.Squid;
import org.sonar.squid.api.Query;
import org.sonar.squid.api.SourceCode;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import de.lgohlke.AST.calculators.DISTANCE_RULE;
import de.lgohlke.AST.visitors.JavaAstVisitorHelper;
import de.lgohlke.AST.visitors.MethodVisitor;
import de.lgohlke.AST.visitors.QDoxFileVisitor;
import de.lgohlke.AST.visitors.VariableTypeDistanceVisitor;

/**
 * <p>
 * AstHelperTest class.
 * </p>
 *
 * @author lars
 * @version $Id: $
 * @since 0.3
 */

public class AstHelperTest
{

  private Squid                         squid;
  private MyVariableTypeDistanceVisitor visitor;
  private MyMethodVisitor               methodVisitor;
  private MyVariableAssignVisitor       variableAssignVisitor;
  private VariableTypeDistanceVisitor variableTypeDistanceVisitor;

  /**
   * <p>
   * setup.
   * </p>
   */
  @Before
  public void setup()
  {
    Registry registry = new Registry();
    squid = new Squid(new JavaSquidConfiguration(true));
    visitor = new MyVariableTypeDistanceVisitor(registry, DISTANCE_RULE.FIRST);
    methodVisitor = new MyMethodVisitor(registry);
    variableAssignVisitor = new MyVariableAssignVisitor(registry);
    variableTypeDistanceVisitor = new VariableTypeDistanceVisitor(registry, DISTANCE_RULE.HIGHEST);

    squid.registerVisitor(new QDoxFileVisitor(registry));
    squid.registerVisitor(visitor);
    squid.registerVisitor(methodVisitor);
    squid.registerVisitor(variableAssignVisitor);
    squid.registerVisitor(variableTypeDistanceVisitor);

  }

  /**
   * <p>
   * findTypeWithCanonicalName.
   * </p>
   */
  @Test
  public void findTypeWithCanonicalName()
  {
    squid.register(JavaAstScanner.class).scanFile(createInputFile("AstHelperTest/StaticInnerClass.java"));

    Assert.assertTrue("eigentlich sollte StaticInnerClass.Metric.LEVEL enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"), visitor
        .getTypes().contains("StaticInnerClass.Metric.LEVEL"));
  }

  /**
   * <p>
   * findTypeTwoDimensionalArray.
   * </p>
   */
  @Test
  public void findTypeTwoDimensionalArray()
  {
    squid.register(JavaAstScanner.class).scanFile(createInputFile("AstHelperTest/TwoDimensionalArray.java"));

    Assert.assertTrue("eigentlich sollte Object enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"),
        visitor.getTypes().contains("Object"));

    Assert
    .assertTrue("eigentlich sollte int enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"), visitor.getTypes().contains("int"));
  }

  @Test
  public void findTypeWithCanonicalType()
  {
    squid.register(JavaAstScanner.class).scanFile(createInputFile("AstHelperTest/ArrayWithCanonicalType.java"));

    Assert.assertTrue("eigentlich sollte StaticInnerClass.Metric.LEVEL enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"), visitor
        .getTypes().contains("org.apache.activemq.broker.Connection"));
  }

  /**
   * <p>
   * findTypeInGenericList.
   * </p>
   */
  @Test
  public void findTypeInGenericList()
  {
    squid.register(JavaAstScanner.class).scanFile(createInputFile("AstHelperTest/Generics.java"));

    Assert.assertEquals("File", visitor.getTypes().get(0));
    Assert.assertTrue("eigentlich sollte List enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"),
        visitor.getTypes().contains("List"));
    Assert
    .assertTrue("eigentlich sollte Map enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"), visitor.getTypes().contains("Map"));
    Assert.assertTrue("eigentlich sollte String enthalten sein, statt dessen :\n" + StringUtils.join(visitor.getTypes(), "\n"),
        visitor.getTypes().contains("String"));

  }

  /**
   * <p>
   * findLastLineOfMethod.
   * </p>
   */
  @Test
  public void findLastLineOfMethod()
  {
    squid.register(JavaAstScanner.class).scanFile(createInputFile("AstHelperTest/MethodLocation.java"));

    Assert.assertEquals(33, methodVisitor.getCurrentLocation().getLineEnd());
  }

  @Test
  public void findLastLineOfMethod2()
  {
    InputFile inputfile = InputFileUtils.create(new File("src/main/java"), new File("src/main/java" + "/de/lgohlke/io/RelatedTestsFinder.java"));
    squid.register(JavaAstScanner.class).scanFile(inputfile);

    // Assert.assertEquals(33, methodVisitor.getCurrentLocation().getLineEnd());
  }

  /**
   * <p>
   * findLastLineOfMethod.
   * </p>
   */
  @Test
  public void findLocationOfMethodInInnerInterface()
  {
    squid.register(JavaAstScanner.class).scanFile(createInputFile("AstHelperTest/MethodLocationOfInnerInterface.java"));

    Assert.assertEquals("AstHelperTest.MethodLocationOfInnerInterface.Test", methodVisitor.getCurrentLocation().getClazz());
    Assert.assertEquals(7, methodVisitor.getCurrentLocation().getLineEnd());
    Assert.assertEquals(7, methodVisitor.getCurrentLocation().getLineStart());
  }

  /**
   * <p>
   * findLastLineOfMethod.
   * </p>
   */
  @Test
  public void findLocationOfMethodInEnum()
  {
    squid.register(JavaAstScanner.class).scanFile(createInputFile("AstHelperTest/MethodLocationOfEnumMethod.java"));

    // RelatedCodeMetricAggregator.debugSE(squid);

    List<Location> locations = methodVisitor.getLocationList();

    Assert.assertEquals(3, locations.size());

    Location firstLocation = locations.get(0);
    Location secondLocation = locations.get(1);
    Location thirdLocation = locations.get(2);

    Assert.assertEquals("AstHelperTest.MethodLocationOfEnumMethod$1", firstLocation.getClazz());
    Assert.assertEquals("AstHelperTest.MethodLocationOfEnumMethod", secondLocation.getClazz());
    Assert.assertEquals("AstHelperTest.MethodLocationOfEnumMethod", thirdLocation.getClazz());
  }

  /**
   * <p>
   * findLastLineOfMethod.
   * </p>
   */
  @Test
  public void findMethodsOfClassButNotMethodsOfAnonymousClasses()
  {
    System.out.println("--------------------");
    methodVisitor.getLocationList().clear();
    for (Location l : methodVisitor.getLocationList())
    {
      System.out.println("" + l);
    }
    squid.register(JavaAstScanner.class).scanFile(createInputFile("AstHelperTest/AnnonymousClassMethodLocation.java"));

    for (Location l : methodVisitor.getLocationList())
    {
      System.out.println("" + l);
    }
    // Assert.assertEquals(1, methodVisitor.getLocationList().size());
  }

  @Test
  public void findPackageName()
  {
    squid.register(JavaAstScanner.class).scanFile(createInputFile("AstHelperTest/de/lgohlke/x/y/Packagenname.java"));

    AstHelper astHelper = methodVisitor.getAstHelper();
    DetailAST ast = methodVisitor.getAstLeaveTokenList().get(0);

    String packageName = astHelper.getPackageName(ast);
    Assert.assertEquals("AstHelperTest.de.lgohlke.x.y", packageName);
  }

  @Test
  public void findImports()
  {
    squid.register(JavaAstScanner.class).scanFile(createInputFile("AstHelperTest/de/lgohlke/x/y/Packagenname.java"));

    DetailAST ast = methodVisitor.getAstLeaveTokenList().get(0);
    AstHelper astHelper = methodVisitor.getAstHelper();
    List<String> imports = astHelper.getImports(ast);

    Assert.assertTrue(imports.contains("A.B.C"));
    Assert.assertTrue(imports.contains("ZY.X"));
  }

  //  @Test
  //  public void findDeclarationLocation()
  //  {
  //    squid.register(JavaAstScanner.class).scanFile(createInputFile("AstHelperTest/VariablenDeclarationLocation.java"));
  //
  //    List<DetailAST> astList = variableAssignVisitor.getAstLeaveTokenList();
  //    DetailAST[] ast = astList.toArray(new DetailAST[astList.size()]);
  //    AstHelper astHelper = variableAssignVisitor.getAstHelper();
  //
  //    Assert.assertEquals("String", astHelper.findDottedTypeOfVarDef(astHelper.getVarDefOfAssign(ast[0])));
  //    Assert.assertEquals("String", astHelper.findDottedTypeOfVarDef(astHelper.getVarDefOfAssign(ast[1])));
  //    Assert.assertEquals("String", astHelper.findDottedTypeOfVarDef(astHelper.getVarDefOfAssign(ast[2])));
  //    Assert.assertEquals("int", astHelper.findDottedTypeOfVarDef(astHelper.getVarDefOfAssign(ast[3])));
  //    Assert.assertEquals("Form", astHelper.findDottedTypeOfVarDef(astHelper.getVarDefOfAssign(ast[4])));
  //  }

  // @Test
  public void testSquidScan()
  {
    Query query = new Query()
    {
      @Override
      public boolean match(final SourceCode unit)
      {
        if (unit.getKey().contains("RelatedCodeMetricAggregatorTest") && unit.getKey().endsWith("#doAggregation()V"))
        {
          System.out.println(unit.getKey());
          return true;
        }
        else
        {
          return false;
        }
      }
    };
    {
      System.out.println("one directory");
      Squid squid = new Squid(new JavaSquidConfiguration(true));
      squid.register(JavaAstScanner.class).scanDirectory(new File("src/test/java"));
      Collection<SourceCode> list = squid.search(query);
      Assert.assertEquals(1, list.size());
    }
    {
      System.out.println("two directories");
      Squid squid = new Squid(new JavaSquidConfiguration(true));
      squid.register(JavaAstScanner.class).scanDirectory(new File("src/main/java"));
      squid.register(JavaAstScanner.class).scanDirectory(new File("src/test/java"));
      Collection<SourceCode> list = squid.search(query);
      Assert.assertEquals(1, list.size());
    }

  }

  private static InputFile createInputFile(final String relativeFile)
  {
    String baseDir = "src/test/resources";
    return InputFileUtils.create(new File(baseDir), new File(baseDir + "/" + relativeFile));
  }

  @RequiredArgsConstructor
  //  @Slf4j
  public static class MyVariableAssignVisitor extends JavaAstVisitor
  {

    public static final List<Integer>   WANTED_TOKENS          = Arrays.asList(TokenTypes.VARIABLE_DEF);
    @Getter
    private List<DetailAST>             astLeaveTokenList      = new ArrayList<DetailAST>();
    private final Registry              registry;
    @Getter
    private AstHelper                   astHelper;

    @Override
    public List<Integer> getWantedTokens()
    {
      return WANTED_TOKENS;
    }

    @Override
    public void visitToken(final DetailAST ast){

    }

    @Override
    public void leaveToken(final DetailAST ast)
    {

      astLeaveTokenList.add(ast);
    }

    @Override
    public void visitFile(final DetailAST ast)
    {
      File file = new File(getFileContents().getFilename());
      String keyFile = file.getAbsolutePath();
      astHelper = new AstHelper(registry, keyFile, this);
    }
  }
}





class MyMethodVisitor extends MethodVisitor
{
  @Getter
  private List<Location>  locationList      = new ArrayList<Location>();
  @Getter
  private List<DetailAST> astLeaveTokenList = new ArrayList<DetailAST>();
  private Registry        registry;
  @Getter
  private AstHelper       astHelper;

  /**
   * <p>
   * Constructor for MyMethodVisitor.
   * </p>
   *
   * @param registry
   *          a {@link de.lgohlke.AST.Registry} object.
   */
  public MyMethodVisitor(final Registry registry)
  {
    super(registry);
    this.registry = registry;
  }

  @Override
  public Location getCurrentLocation()
  {
    return super.getCurrentLocation();
  }

  @Override
  public void leaveToken(final DetailAST ast)
  {
    super.leaveToken(ast);
    locationList.add(getCurrentLocation());
    astLeaveTokenList.add(ast);
  }

  @Override
  public void visitFile(final DetailAST ast)
  {
    super.visitFile(ast);
    File file = new File(getFileContents().getFilename());
    String keyFile = file.getAbsolutePath();
    astHelper = new AstHelper(registry, keyFile, this);
  }
}

class MyVariableTypeDistanceVisitor extends VariableTypeDistanceVisitor
{

  private String                     key;
  private AstHelper                  astHelper;
  private final Registry             registry;
  private final JavaAstVisitorHelper visitorHelper;

  @Getter
  private final List<String>         types = new ArrayList<String>();

  /**
   * <p>
   * Constructor for MyVariableTypeDistanceVisitor.
   * </p>
   *
   * @param registry
   *          a {@link de.lgohlke.AST.Registry} object.
   * @param rule
   *          a {@link de.lgohlke.AST.calculators.DISTANCE_RULE} object.
   */
  public MyVariableTypeDistanceVisitor(final Registry registry, final DISTANCE_RULE rule)
  {
    super(registry, rule);
    this.registry = registry;
    this.visitorHelper = new JavaAstVisitorHelper(this);
  }

  /** {@inheritDoc} */
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
}
