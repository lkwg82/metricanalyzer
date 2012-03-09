package de.lgohlke.AST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.sonar.java.ast.visitor.AstUtils;
import org.sonar.java.ast.visitor.JavaAstVisitor;
import org.sonar.java.signature.JvmJavaType;
import org.sonar.java.signature.MethodSignature;
import org.sonar.java.signature.MethodSignaturePrinter;
import org.sonar.java.signature.Parameter;

import antlr.collections.AST;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import de.lgohlke.io.AstProcessorException;

/**
 * <p>
 * AstHelper class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
public class AstHelper
{
  private static final Map<Integer, JvmJavaType> TOKEN_JAVA_TYPE_MAPPING = new HashMap<Integer, JvmJavaType>();

  private static final Integer[]                 LITERAL_TOKEN_TYPES     = new Integer[] { TokenTypes.LITERAL_INT, TokenTypes.LITERAL_BOOLEAN,
    TokenTypes.LITERAL_BYTE, TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_DOUBLE, TokenTypes.LITERAL_FLOAT, TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_SHORT };

  static
  {
    TOKEN_JAVA_TYPE_MAPPING.put(TokenTypes.LITERAL_BYTE, JvmJavaType.B);
    TOKEN_JAVA_TYPE_MAPPING.put(TokenTypes.LITERAL_CHAR, JvmJavaType.C);
    TOKEN_JAVA_TYPE_MAPPING.put(TokenTypes.LITERAL_SHORT, JvmJavaType.S);
    TOKEN_JAVA_TYPE_MAPPING.put(TokenTypes.LITERAL_INT, JvmJavaType.I);
    TOKEN_JAVA_TYPE_MAPPING.put(TokenTypes.LITERAL_LONG, JvmJavaType.J);
    TOKEN_JAVA_TYPE_MAPPING.put(TokenTypes.LITERAL_BOOLEAN, JvmJavaType.Z);
    TOKEN_JAVA_TYPE_MAPPING.put(TokenTypes.LITERAL_FLOAT, JvmJavaType.F);
    TOKEN_JAVA_TYPE_MAPPING.put(TokenTypes.LITERAL_DOUBLE, JvmJavaType.D);
    TOKEN_JAVA_TYPE_MAPPING.put(TokenTypes.LITERAL_VOID, JvmJavaType.V);
  }
  private static final String                    CONSTRUCTOR             = "<init>";
  private final JavaAstVisitor                   visitor;

  private final Registry                         registry;
  private final String                           key;

  /**
   * <p>
   * Constructor for AstHelper.
   * </p>
   * 
   * @param registry
   *          a {@link de.lgohlke.AST.Registry} object.
   * @param key
   *          a {@link java.lang.String} object.
   * @param visitor
   *          a {@link org.sonar.java.ast.visitor.JavaAstVisitor} object.
   */
  public AstHelper(final Registry registry, final String key, final JavaAstVisitor visitor)
  {
    this.key = key;
    this.registry = registry;
    this.visitor = visitor;
  }

  private Parameter extractMethodReturnType(final DetailAST ast, final boolean isConstructor)
  {
    if (isConstructor)
    {
      return new Parameter(JvmJavaType.V, false);
    }
    Parameter returnType = extractArgumentAndReturnType(ast.findFirstToken(TokenTypes.TYPE));
    return new Parameter(returnType);
  }

  private Parameter extractArgumentAndReturnType(final DetailAST ast)
  {
    boolean isArray = isArrayType(ast);
    for (Integer tokenType : TOKEN_JAVA_TYPE_MAPPING.keySet())
    {
      if (ast.branchContains(tokenType))
      {
        return new Parameter(TOKEN_JAVA_TYPE_MAPPING.get(tokenType), isArray);
      }
    }

    if (isObjectType(ast))
    {
      String className;
      if (isArray)
      {
        className = extractClassName(drilldownToLastArray(ast));
      }
      else
      {
        className = extractClassName(ast);
      }
      return new Parameter(className, isArray);
    }
    throw new IllegalStateException("No Known TokenType has been found at line " + ast.getLineNo() + " of file " + visitor.getFileContents().getFilename());
  }

  private DetailAST drilldownToLastArray(final DetailAST ast)
  {
    DetailAST myAst = ast;
    while (myAst.findFirstToken(TokenTypes.ARRAY_DECLARATOR) != null)
    {
      myAst = myAst.findFirstToken(TokenTypes.ARRAY_DECLARATOR);
    }
    return myAst;
  }

  private String extractClassName(final DetailAST ast)
  {
    if (ast.findFirstToken(TokenTypes.DOT) != null)
    {
      return findLastToken(ast.findFirstToken(TokenTypes.DOT), TokenTypes.IDENT).getText();
    }
    else
    {
      return findLastToken(ast, TokenTypes.IDENT).getText();
    }
  }

  private boolean isObjectType(final DetailAST ast)
  {
    return ast.branchContains(TokenTypes.IDENT);
  }

  private boolean isArrayType(final DetailAST ast)
  {
    return (ast.findFirstToken(TokenTypes.IDENT) == null) && (ast.branchContains(TokenTypes.ARRAY_DECLARATOR));
  }

  private String extractMethodName(final DetailAST ast, final boolean isConstructor)
  {
    if (isConstructor)
    {
      return CONSTRUCTOR;
    }
    return ast.findFirstToken(TokenTypes.IDENT).getText();
  }

  private DetailAST findLastToken(final DetailAST astNode, final int tokenType)
  {
    DetailAST retVal = null;
    for (AST child = astNode.getFirstChild(); child != null; child = child.getNextSibling())
    {
      if (child.getType() == tokenType)
      {
        retVal = (DetailAST) child;
      }
    }
    return retVal;
  }

  private List<Parameter> extractMethodArgumentTypes(final DetailAST ast)
  {
    List<Parameter> argumentTypes = new ArrayList<Parameter>();
    DetailAST child = ast.findFirstToken(TokenTypes.PARAMETERS).findFirstToken(TokenTypes.PARAMETER_DEF);
    while (child != null)
    {
      if (child.getType() == TokenTypes.PARAMETER_DEF)
      {
        Parameter argumentType = extractArgumentAndReturnType(child.findFirstToken(TokenTypes.TYPE));
        argumentTypes.add(new Parameter(argumentType));
      }
      child = child.getNextSibling();
    }
    return argumentTypes;
  }

  /**
   * <p>
   * isConstructor.
   * </p>
   * 
   * @param ast
   *          a {@link com.puppycrawl.tools.checkstyle.api.DetailAST} object.
   * @return a boolean.
   */
  public boolean isConstructor(final DetailAST ast)
  {
    return ast.getType() == TokenTypes.CTOR_DEF;
  }

  /**
   * <p>
   * buildMethodSignature.
   * </p>
   * 
   * @param ast
   *          a {@link com.puppycrawl.tools.checkstyle.api.DetailAST} object.
   * @param isConstructor
   *          a boolean.
   * @return a {@link java.lang.String} object.
   */
  public String buildMethodSignature(final DetailAST ast, final boolean isConstructor)
  {
    String methodName = extractMethodName(ast, isConstructor);
    Parameter returnType = extractMethodReturnType(ast, isConstructor);
    List<Parameter> argumentTypes = extractMethodArgumentTypes(ast);
    MethodSignature signature = new MethodSignature(methodName, returnType, argumentTypes);
    return MethodSignaturePrinter.print(signature);
  }

  /**
   * extracts from a DetailAST the full Classname
   * 
   * @param ast
   *          a {@link com.puppycrawl.tools.checkstyle.api.DetailAST} object.
   * @return a {@link java.lang.String} object.
   */
  public String getFullClassName(final DetailAST ast)
  {
    return getFullClassName(ast, new LinkedList<String>());
  }

  private String getFullClassName(final DetailAST ast, final List<String> classes)
  {
    String result;
    if (AstUtils.isClass(ast) || (ast.getType() == TokenTypes.ENUM_CONSTANT_DEF))
    {

      if (ast.getType() == TokenTypes.ENUM_CONSTANT_DEF)
      {
        int index = 1;
        DetailAST previous = ast.getPreviousSibling();
        while (previous != null)
        {
          if (previous.getType() == TokenTypes.ENUM_CONSTANT_DEF)
          {
            index++;
          }
          // log.debug(previous.getText());
          previous = previous.getPreviousSibling();
        }
        classes.add("$" + index);
        // ENUM_CONSTANT_DEF -> OBJBlock -> ENUM_DEF
        result = getFullClassName(ast.getParent().getParent(), classes);
      }
      else
      {
        String className = ast.findFirstToken(TokenTypes.IDENT).getText();

        classes.add(className);
        DetailAST astClass = AstUtils.findParent(ast, TokenTypes.CLASS_DEF);
        boolean isNoInnerClass = astClass == null;
        if (isNoInnerClass)
        {

          String packageName = registry.getPackageName().get(key);
          // /**
          // * TODO test again with QDOX 2 and try an annotation with \\ in see see
          // * http://jira.codehaus.org/browse/QDOX-230
          // * <p/>
          // * WORKAROUND: we early collect the packagenames in the QDoxScanner-process, but when there is an error, we
          // * have to handle this null and retrieve the package name from the current ast
          // */
          // if (packageName == null)
          // {
          // packageName = getPackageName(ast);
          // }
          if ( packageName == null){
            result = "";
          }else{
            StringBuffer canonicalClassName = new StringBuffer(packageName);
            for (int i = classes.size() - 1; i >= 0; i--)
            {
              String clazz = classes.get(i);
              if (!clazz.startsWith("$"))
              {
                canonicalClassName.append(".");
              }
              canonicalClassName.append(clazz);
            }
            result = canonicalClassName.toString();
          }}
        else
        {
          result = getFullClassName(astClass, classes);
        }
      }
    }
    else
    {
      result = getFullClassName(AstUtils.findParent(ast, TokenTypes.CLASS_DEF), classes);
    }
    return result;
  }

  /**
   * <p>
   * getCurrentClass.
   * </p>
   * 
   * @param ast
   *          a {@link com.puppycrawl.tools.checkstyle.api.DetailAST} object.
   * @return a {@link java.lang.String} object.
   * @throws AstProcessorException
   */
  public String getCurrentClass(final DetailAST ast)
  {
    DetailAST resultAst = null;

    // System.out.print(ast.getText() + " " + ast.findFirstToken(TokenTypes.IDENT).getText());
    DetailAST astClazz = AstUtils.findParent(ast, TokenTypes.CLASS_DEF);

    // not found, when method in ENUM
    if (astClazz == null)
    {
      /**
       * <pre>
       * class X
       * {
       *   enum Y
       *   {
       *   A{
       *      public void test(){}
       *   }
       *   abstract void test();
       *   }
       * }
       * </pre>
       */
      DetailAST astEnum = AstUtils.findParent(ast, TokenTypes.ENUM_DEF);

      if (astEnum == null)
      {
        /**
         * <pre>
         * interface X
         * {
         *   void test();
         * }
         * </pre>
         */
        resultAst = AstUtils.findParent(ast, TokenTypes.INTERFACE_DEF);
      }
      else
      {
        /**
         * <pre>
         * public enum MethodLocationOfEnumMethod
         * {
         *   A()
         *   {
         *     &#064;Override
         *     private void method()
         *     {
         *     };
         *   };
         * 
         *   public abstract void method();
         * 
         *   private void test()
         *   {
         *   };
         * }
         * </pre>
         */
        DetailAST astEnumCONSTANT = AstUtils.findParent(ast, TokenTypes.ENUM_CONSTANT_DEF);
        boolean isInInnerEnumConstant = astEnumCONSTANT != null;
        if (isInInnerEnumConstant)
        {
          resultAst = astEnumCONSTANT;
        }
        else
        {
          resultAst = astEnum;
        }
      }
    }
    else
    {
      /**
       * <pre>
       * ...
       *      RouteBuilder x = new RouteBuilder(){
       *        public void configure(){}
       *      };
       * ...
       * </pre>
       */
      DetailAST astNew = AstUtils.findParent(ast, TokenTypes.LITERAL_NEW);

      boolean hasAnonymousClassDeclaration = (astNew != null);

      if (hasAnonymousClassDeclaration)
      {
        return "anonymousInner#" + getFullClassName(astClazz);
      }
      else
      {
        /**
         * <pre>
         * class Parent
         * {
         *   interface X
         *   {
         *     void test();
         *   }
         * }
         * </pre>
         */
        DetailAST astInterface = AstUtils.findParent(ast, TokenTypes.INTERFACE_DEF);

        boolean hasInnerInterface = (astInterface != null);
        if (hasInnerInterface)
        {
          resultAst = astInterface;
        }
        else
        {
          resultAst = astClazz;
        }
      }
    }

    if (resultAst == null)
    {
      throw new RuntimeException(new AstProcessorException("could not find class for " + ast));
    }
    else
    {
      return getFullClassName(resultAst);
    }
  }

  private DetailAST findIdentifierInMultidimensionalArray(final DetailAST child)
  {
    DetailAST result;
    if (child.getType() == TokenTypes.ARRAY_DECLARATOR)
    {
      // ident?
      DetailAST typeToken = child.findFirstToken(TokenTypes.IDENT);

      for (int i = 0; (i < LITERAL_TOKEN_TYPES.length) && (typeToken == null); i++)
      {
        typeToken = child.findFirstToken(LITERAL_TOKEN_TYPES[i]);
      }

      if (typeToken == null)
      {
        result = findIdentifierInMultidimensionalArray(child.getFirstChild());
      }
      else
      {
        result = typeToken;
      }
    }
    else if (child.getType() == TokenTypes.DOT)
    {
      // get type when in canonical form
      // org.apache.activemq.broker.Connection[] xyz = ...
      Set<DetailAST> set = findGenericTypes(child);
      result = set.toArray(new DetailAST[1])[0];
    }
    else
    {
      log.error("something went wrong only " + TokenTypes.ARRAY_DECLARATOR + "(ARRAY_DECLARATOR) should reached this location in line:" + child.getLineNo());
      result = null;
    }
    return result;
  }

  public void findCompleteDottedType(final DetailAST ast, final List<String> types)
  {
    if (ast.getFirstChild().getType() == TokenTypes.DOT)
    {
      DetailAST myAst = ast.getFirstChild();

      if (myAst.getNextSibling().getType() == TokenTypes.IDENT)
      {
        findCompleteDottedType(myAst, types);
        types.add(myAst.getNextSibling().getText());
      }
    }
    else
    {
      if (ast.getChildCount() == 2)
      {
        DetailAST myAst = ast.getFirstChild();

        if ((myAst.getType() == TokenTypes.IDENT) && (myAst.getNextSibling().getType() == TokenTypes.IDENT))
        {
          types.add(myAst.getText());
          types.add(myAst.getNextSibling().getText());
        }
      }
    }
  }

  /**
   * verschachtelten Typen, wie Generics sollen alle Typen herausgefunden werden
   * 
   * <pre>
   * List&lt;Map&lt;String,Integer[]&gt;&gt; listMap = ...
   * </pre>
   * 
   * soll dann { List,Map,String,Integer } ergeben
   * 
   * @param ast
   *          a {@link com.puppycrawl.tools.checkstyle.api.DetailAST} object.
   * @return a {@link java.util.Set} object.
   */
  public Set<DetailAST> findGenericTypes(final DetailAST ast)
  {
    Set<DetailAST> astSet;

    switch (ast.getType())
    {
      case TokenTypes.VARIABLE_DEF:
      case TokenTypes.METHOD_DEF:
        DetailAST typeAst = ast.findFirstToken(TokenTypes.TYPE);
        astSet = findGenericTypes(typeAst);
        break;
      case TokenTypes.TYPE:
      case TokenTypes.TYPE_ARGUMENT:
      {
        DetailAST childAst = ast.getFirstChild();
        astSet = findGenericTypes(childAst);
      }
      break;
      case TokenTypes.IDENT:
      {
        astSet = new HashSet<DetailAST>();

        astSet.add(ast);
        DetailAST currentAst = ast;
        while (currentAst.getNextSibling() != null)
        {
          currentAst = currentAst.getNextSibling();
          astSet.addAll(findGenericTypes(currentAst));
        }
      }
      break;
      case TokenTypes.DOT:
      {
        List<String> types = new ArrayList<String>();
        findCompleteDottedType(ast, types);

        DetailAST resultAst = new DetailAST();
        resultAst.setText(StringUtils.join(types, "."));

        astSet = new HashSet<DetailAST>();
        astSet.add(resultAst);
      }
      break;
      case TokenTypes.TYPE_ARGUMENTS:
      {
        astSet = new HashSet<DetailAST>();

        DetailAST currentAst = ast.getFirstChild();
        while (currentAst.getNextSibling() != null)
        {
          currentAst = currentAst.getNextSibling();
          astSet.addAll(findGenericTypes(currentAst));
        }
      }
      break;
      case TokenTypes.ARRAY_DECLARATOR:
      {
        astSet = new HashSet<DetailAST>();
        astSet.add(findIdentifierInMultidimensionalArray(ast));
      }
      break;
      default:
        astSet = new HashSet<DetailAST>();
        break;
    }

    return astSet;
  }

  /**
   * <p>
   * findLastLineOfMethod.
   * </p>
   * 
   * @param ast
   *          a {@link com.puppycrawl.tools.checkstyle.api.DetailAST} object.
   * @return a int.
   */
  public int findLastLineOfMethod(final DetailAST ast)
  {
    int result;
    if (ast.getChildCount() > 0)
    {
      DetailAST child = ast.findFirstToken(TokenTypes.SLIST);
      if (child == null)
      {
        result = ast.getLineNo();
      }
      else
      {
        DetailAST end = child.findFirstToken(TokenTypes.RCURLY);
        result = end.getLineNo();
      }
    }
    else
    {
      result = 0;
    }
    return result;
  }

  private DetailAST findFirstPreviousSiblingByType(final DetailAST ast, final int type)
  {
    DetailAST previousSibling = ast.getPreviousSibling();

    if (previousSibling == null)
    {
      return null;
    }
    else
    {
      if (previousSibling.getType() == type)
      {
        return previousSibling;
      }
      else
      {
        return findFirstPreviousSiblingByType(previousSibling, type);
      }
    }
  }

  public String findDottedName(final DetailAST ast)
  {
    DetailAST dotAst = ast.findFirstToken(TokenTypes.DOT);
    if (dotAst == null)
    {
      List<Integer> literalTokenList = Arrays.asList(LITERAL_TOKEN_TYPES);
      if ((ast.getChildCount() > 0) && ((ast.getFirstChild().getType() == TokenTypes.IDENT) || (literalTokenList.contains(ast.getFirstChild().getType()))))
      {
        if ((ast.getChildCount() == 2) && (ast.getLastChild().getType() == TokenTypes.IDENT))
        {
          return ast.getFirstChild().getText() + "." + ast.getLastChild().getText();
        }
        else
        {
          return ast.getFirstChild().getText();
        }
      }
      else
      {
        throw new RuntimeException("logical error: the last dot only have two idents");
      }
    }
    else
    {
      String dotQuery = findDottedName(dotAst);
      DetailAST nextSibling = dotAst.getNextSibling();
      if (nextSibling == null)
      {
        throw new RuntimeException("dont know any expression with DOTS in a row????");
      }
      else
      {
        if (nextSibling.getType() == TokenTypes.IDENT)
        {
          if (dotQuery == null)
          {
            return nextSibling.getText();
          }
          else
          {
            return dotQuery + "." + nextSibling.getText();
          }
        }
        // end of retrieval, we are back on the top
        else if (nextSibling.getType() == TokenTypes.SEMI)
        {
          return dotQuery;
        }
        else
        {
          throw new RuntimeException("logical error: next sibling is no ident?");
        }
      }
    }
  }

  public String getPackageName(final DetailAST ast)
  {
    DetailAST packageAst;
    if (ast.getType() == TokenTypes.PACKAGE_DEF)
    {
      packageAst = ast;
    }
    else
    {
      DetailAST classAst;
      if (ast.getType() == TokenTypes.CLASS_DEF)
      {
        classAst = ast;
      }
      else
      {
        classAst = AstUtils.findParent(ast, TokenTypes.CLASS_DEF);
      }
      packageAst = findFirstPreviousSiblingByType(classAst, TokenTypes.PACKAGE_DEF);
    }

    String name = findDottedName(packageAst);

    return name;
  }

  public List<String> getImports(final DetailAST ast)
  {
    List<DetailAST> importAstList;
    if (ast.getType() == TokenTypes.PACKAGE_DEF)
    {
      importAstList = findAllNextSiblingsWithType(ast, TokenTypes.IMPORT);
    }
    else
    {
      DetailAST classAst;
      if (ast.getType() == TokenTypes.CLASS_DEF)
      {
        classAst = ast;
      }
      else
      {
        classAst = AstUtils.findParent(ast, TokenTypes.CLASS_DEF);
      }

      importAstList = findAllPreviousSiblingsWithType(classAst, TokenTypes.IMPORT);
    }
    List<String> imports = new ArrayList<String>();
    for (DetailAST importAst : importAstList)
    {
      String name = findDottedName(importAst);
      imports.add(name);
    }
    return imports;
  }

  private List<DetailAST> findAllPreviousSiblingsWithType(final DetailAST ast, final int type)
  {
    List<DetailAST> results = new ArrayList<DetailAST>();

    DetailAST previousSibling = ast.getPreviousSibling();

    if (previousSibling != null)
    {
      if (previousSibling.getType() == type)
      {
        results.add(previousSibling);
      }
      results.addAll(findAllPreviousSiblingsWithType(previousSibling, type));
    }

    return results;
  }

  private List<DetailAST> findAllNextSiblingsWithType(final DetailAST ast, final int type)
  {
    List<DetailAST> results = new ArrayList<DetailAST>();

    DetailAST nextSibling = ast.getNextSibling();

    if (nextSibling != null)
    {
      if (nextSibling.getType() == type)
      {
        results.add(nextSibling);
      }
      results.addAll(findAllNextSiblingsWithType(nextSibling, type));
    }

    return results;
  }

  public String getIdentifierFromASSIGN(final DetailAST ast)
  {
    if (ast.getType() == TokenTypes.ASSIGN)
    {

      // try with declaration : String x = "x";
      DetailAST identAst = ast.getPreviousSibling();
      if (identAst == null)
      {
        if (ast.getFirstChild().getType() == TokenTypes.IDENT)
        {
          // without declaration x = "x";
          identAst = ast.getFirstChild();
        }
        else if (ast.getFirstChild().getType() == TokenTypes.DOT)
        {
          // this.x = "x"
          DetailAST dotAst = ast.getFirstChild();
          if ((dotAst.getChildCount() == 2) && (dotAst.getFirstChild().getType() == TokenTypes.LITERAL_THIS))
          {
            identAst = dotAst.getLastChild();
          }
          else
          {
            throw new RuntimeException(new AstProcessorException("not handled yet"));
          }
        }
        else if (ast.getFirstChild().getType() == TokenTypes.INDEX_OP)
        {
          identAst = ast.getFirstChild().findFirstToken(TokenTypes.IDENT);
        }
        else
        {
          throw new RuntimeException(new AstProcessorException("not handled yet"));
        }
      }
      return identAst.getText();
    }
    return null;
  }

  public DetailAST getVarDefOfAssign(final DetailAST ast)
  {
    if (ast.getType() == TokenTypes.ASSIGN)
    {
      @RequiredArgsConstructor
      class VarDefQuery implements ASTQuery
      {
        private final DetailAST identAstGiveAway;

        @Override
        public boolean recursive()
        {
          return true;
        }

        @Override
        public boolean match(final DetailAST ast)
        {
          if (ast.getType() == TokenTypes.VARIABLE_DEF)
          {
            // down to the IDENT
            DetailAST identAst = findFirstASTDownwards(ast, new ASTQuery()
            {

              @Override
              public boolean match(final DetailAST ast)
              {
                if (ast.getType() == TokenTypes.IDENT)
                {
                  return ast.getText().equals(identAstGiveAway.getText());
                }
                else
                {
                  return false;
                }
              }

              @Override
              public boolean recursive()
              {
                return true;
              }

              @Override
              public boolean findAll()
              {
                return false;
              }
            });

            return identAst != null;
          }
          else
          {
            return false;
          }
        }

        @Override
        public boolean findAll()
        {
          return false;
        }
      }

      DetailAST varDefAst = null;
      // try with declaration : String x = "x";
      DetailAST identAst = ast.getPreviousSibling();
      if (identAst == null)
      {
        if (ast.getFirstChild().getType() == TokenTypes.IDENT)
        {
          // without declaration x = "x";
          identAst = ast.getFirstChild();
        }
        else if (ast.getFirstChild().getType() == TokenTypes.DOT)
        {
          // this.x = "x"
          DetailAST dotAst = ast.getFirstChild();
          if ((dotAst.getChildCount() == 2) && (dotAst.getFirstChild().getType() == TokenTypes.LITERAL_THIS))
          {
            identAst = dotAst.getLastChild();

            DetailAST clasDefAst = AstUtils.findParent(ast, TokenTypes.CLASS_DEF);

            varDefAst = findFirstASTDownwards(clasDefAst, new VarDefQuery(identAst));
          }
          else
          {
            throw new RuntimeException(new AstProcessorException("not handled yet"));
          }
        }
        else if (ast.getFirstChild().getType() == TokenTypes.INDEX_OP)
        {
          identAst = ast.getFirstChild().findFirstToken(TokenTypes.IDENT);
        }
        else
        {
          throw new RuntimeException(new AstProcessorException("not handled yet"));
        }
      }

      if (varDefAst == null)
      {
        // up to the VAR_DEF
        varDefAst = findFirstASTUpwards(identAst, new VarDefQuery(identAst));
      }

      return varDefAst;
    }
    else
    {
      return null;
    }
  }

  public String findDottedTypeOfVarDef(final DetailAST ast)
  {
    if (ast.getType() == TokenTypes.VARIABLE_DEF)
    {
      DetailAST typeAst = ast.findFirstToken(TokenTypes.TYPE);
      return findDottedName(typeAst);
    }
    else
    {
      return null;
    }
  }

  public DetailAST findFirstASTDownwards(final DetailAST ast, final ASTQuery query)
  {
    List<DetailAST> results = findASTDownwards(ast, new ASTQuery()
    {

      @Override
      public boolean recursive()
      {
        return query.recursive();
      }

      @Override
      public boolean match(final DetailAST ast)
      {
        return query.match(ast);
      }

      @Override
      public boolean findAll()
      {
        return false;
      }
    }, new ArrayList<DetailAST>());

    if (results.size() == 1)
    {
      return results.get(0);
    }
    else
    {
      return null;
    }
  }

  public DetailAST findFirstASTUpwards(final DetailAST ast, final ASTQuery query)
  {
    List<DetailAST> results = findASTUpwards(ast, new ASTQuery()
    {

      @Override
      public boolean recursive()
      {
        return query.recursive();
      }

      @Override
      public boolean match(final DetailAST ast)
      {
        return query.match(ast);
      }

      @Override
      public boolean findAll()
      {
        return false;
      }
    });

    if (results.size() == 1)
    {
      return results.get(0);
    }
    else
    {
      return null;
    }
  }

  public List<DetailAST> findASTDownwards(final DetailAST ast, final ASTQuery query, final List<DetailAST> steps)
  {
    List<DetailAST> results = new ArrayList<DetailAST>();

    if (query.recursive())
    {
      DetailAST firstChild = ast.getFirstChild();
      if (firstChild == null)
      {
        DetailAST nextSibling = ast.getNextSibling();
        if (nextSibling != null)
        {
          //          log.debug(nextSibling + "");
          steps.add(nextSibling);
          if (query.match(nextSibling))
          {
            results.add(nextSibling);
            if (query.findAll())
            {
              results.addAll(findASTDownwards(nextSibling, query, steps));
            }
          }
          else
          {
            results.addAll(findASTDownwards(nextSibling, query, steps));
          }
        }
      }
      else
      {
        //        log.debug(firstChild + "");
        steps.add(firstChild);
        if (query.match(firstChild))
        {
          results.add(firstChild);
          if (query.findAll())
          {
            results.addAll(findASTDownwards(firstChild, query, steps));
          }
          else
          {
            return results;
          }
        }
        else
        {
          results.addAll(findASTDownwards(firstChild, query, steps));
        }
      }
    }
    if ((results.size() == 0) || query.findAll())
    {
      DetailAST nextSibling = ast.getNextSibling();
      if (nextSibling != null)
      {
        steps.add(nextSibling);
        //        log.debug(nextSibling + "");
        if (query.match(nextSibling))
        {
          results.add(nextSibling);
          if (query.findAll())
          {
            results.addAll(findASTDownwards(nextSibling, query, steps));
          }
        }
        else
        {
          results.addAll(findASTDownwards(nextSibling, query, steps));
        }
      }
    }

    return results;
  }

  public List<DetailAST> findASTUpwards(final DetailAST ast, final ASTQuery query)
  {
    List<DetailAST> results = new ArrayList<DetailAST>();

    DetailAST previousSibling = ast.getPreviousSibling();

    if (previousSibling == null)
    {
      if (query.recursive())
      {
        DetailAST parent = ast.getParent();
        if (parent != null)
        {
          if (query.match(parent))
          {
            results.add(parent);
            if (query.findAll())
            {
              results.addAll(findASTUpwards(parent, query));
            }
            else
            {
              return results;
            }
          }
          else
          {
            results.addAll(findASTUpwards(parent, query));
          }

        }
      }
    }
    else
    {
      if (query.match(previousSibling))
      {
        results.add(previousSibling);
        if (query.findAll())
        {
          results.addAll(findASTUpwards(previousSibling, query));
        }
      }
      else
      {
        results.addAll(findASTUpwards(previousSibling, query));
      }
    }

    return results;
  }

  public interface ASTQuery
  {
    boolean match(DetailAST ast);

    boolean recursive();

    boolean findAll();
  }

  public DetailAST findIdentAst(final DetailAST ast)
  {
    if (ast.getType() == TokenTypes.VARIABLE_DEF)
    {
      DetailAST typeAST = ast.findFirstToken(TokenTypes.TYPE);
      DetailAST identAst = typeAST.getNextSibling();

      return identAst;
    }
    return null;

  }
}
