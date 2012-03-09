package de.lgohlke.AST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.java.ast.visitor.AstUtils;
import org.sonar.java.ast.visitor.JavaAstVisitor;
import org.sonar.java.signature.JvmJavaType;
import org.sonar.java.signature.MethodSignature;
import org.sonar.java.signature.MethodSignaturePrinter;
import org.sonar.java.signature.Parameter;

import antlr.collections.AST;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class AstHelper
{
  private final static Logger                    log                     = LoggerFactory.getLogger(AstHelper.class);
  private static final Map<Integer, JvmJavaType> TOKEN_JAVA_TYPE_MAPPING = new HashMap<Integer, JvmJavaType>();

  private static final int[]                     LITERAL_TOKEN_TYPES     = new int[]
                                                                         { TokenTypes.LITERAL_INT, TokenTypes.LITERAL_BOOLEAN, TokenTypes.LITERAL_BYTE,
      TokenTypes.LITERAL_CHAR, TokenTypes.LITERAL_DOUBLE, TokenTypes.LITERAL_FLOAT, TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_SHORT };

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

  public boolean isConstructor(final DetailAST ast)
  {
    return ast.getType() == TokenTypes.CTOR_DEF;
  }

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
   * @return
   */
  public String getFullClassName(final DetailAST ast)
  {
    return getFullClassName(ast, new LinkedList<String>());
  }

  private String getFullClassName(final DetailAST ast, final List<String> classes)
  {
    if (AstUtils.isClass(ast))
    {
      String className = ast.findFirstToken(TokenTypes.IDENT).getText();

      classes.add(className);

      DetailAST astClass = AstUtils.findParent(ast, TokenTypes.CLASS_DEF);
      if (astClass == null)
      {
        StringBuffer canonicalClassName = new StringBuffer(registry.getPackageName().get(key));
        for (int i = classes.size() - 1; i >= 0; i--)
        {
          canonicalClassName.append(".");
          canonicalClassName.append(classes.get(i));
        }
        return canonicalClassName.toString();
      }
      else
      {
        return getFullClassName(astClass, classes);
      }
    }
    else
    {
      return getFullClassName(AstUtils.findParent(ast, TokenTypes.CLASS_DEF), classes);
    }
  }

  public String getCurrentClass(final DetailAST ast)
  {
    // System.out.print(ast.getText() + " " + ast.findFirstToken(TokenTypes.IDENT).getText());
    DetailAST astClazz = AstUtils.findParent(ast, TokenTypes.CLASS_DEF);

    // not found, when method in ENUM
    if (astClazz == null)
    {
      astClazz = AstUtils.findParent(ast, TokenTypes.ENUM_DEF);
    }

    // not found, when method in Interface
    if (astClazz == null)
    {
      astClazz = AstUtils.findParent(ast, TokenTypes.INTERFACE_DEF);
    }

    return getFullClassName(astClazz);
  }

  private DetailAST findIdentifierInMultidimensionalArray(final DetailAST child)
  {
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
        return findIdentifierInMultidimensionalArray(child.getFirstChild());
      }
      else
      {
        return typeToken;
      }
    }
    else
    {
      log.error("something went wrong only " + TokenTypes.ARRAY_DECLARATOR + " should reached this location");
      return null;
    }
  }

  private void findCompleteDottedType(final DetailAST ast, final List<String> types)
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
   * List<Map<String,Integer[]>> listMap = ...
   * </pre>
   * 
   * soll dann { List,Map,String,Integer } ergeben
   * 
   * @param ast
   * @return
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
}
