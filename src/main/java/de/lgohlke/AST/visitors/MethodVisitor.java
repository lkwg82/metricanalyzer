package de.lgohlke.AST.visitors;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.sonar.java.ast.visitor.JavaAstVisitor;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import de.lgohlke.AST.AstHelper;
import de.lgohlke.AST.Location;
import de.lgohlke.AST.Registry;

/**
 * <p>MethodVisitor class.</p>
 *
 * @author lars
 * @version $Id: $
 */
@Slf4j
@RequiredArgsConstructor
public class MethodVisitor extends JavaAstVisitor
{
  /** Constant <code>WANTED_TOKENS</code> */
  public static final List<Integer> WANTED_TOKENS = Arrays.asList(TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF);
  private final Registry            registry;
  private AstHelper                 astHelper     = null;
  private String                    keyFile;
  @Getter
  private Location                  currentLocation;
  /**
   * sometimes there are a method in a method (when a method calls an anonymous interface instance)
   * so we need the original location, not the overwritten
   */
  private Stack<Location> nestedLocations = new Stack<Location>();


  /* (non-Javadoc)
   * @see org.sonar.java.ast.visitor.JavaAstVisitor#getWantedTokens()
   */
  @Override
  public List<Integer> getWantedTokens()
  {
    // TODO: do we really need constructor-inspection?
    return WANTED_TOKENS;
  }

  /* (non-Javadoc)
   * @see org.sonar.java.ast.visitor.JavaAstVisitor#visitToken(com.puppycrawl.tools.checkstyle.api.DetailAST)
   */
  @Override
  public void visitToken(final DetailAST ast)
  {
    String clazz = astHelper.getCurrentClass(ast);
    String signature = astHelper.buildMethodSignature(ast, ast.getType() == TokenTypes.CTOR_DEF);
    String key = clazz + "#" + signature;
    key = key.replace('.', '/');

    currentLocation = new Location();
    currentLocation.setKey(key);
    currentLocation.setClazz(clazz);
    currentLocation.setFile(keyFile);
    currentLocation.setLineStart(ast.getLineNo());
    currentLocation.setMethodSignature(signature);

    nestedLocations.push(currentLocation);

    if (log.isDebugEnabled()){
      log.debug("pushing onto stack : " + currentLocation);
    }
  }

  /* (non-Javadoc)
   * @see org.sonar.java.ast.visitor.JavaAstVisitor#leaveToken(com.puppycrawl.tools.checkstyle.api.DetailAST)
   */
  @Override
  public void leaveToken(final DetailAST ast)
  {
    currentLocation = nestedLocations.pop();
    currentLocation.setLineEnd(astHelper.findLastLineOfMethod(ast));
    registry.getLocationList().add(currentLocation);
  }

  /* (non-Javadoc)
   * @see org.sonar.java.ast.visitor.JavaAstVisitor#visitFile(com.puppycrawl.tools.checkstyle.api.DetailAST)
   */
  @Override
  public void visitFile(final DetailAST ast)
  {
    File file = new File(getFileContents().getFilename());
    keyFile = file.getAbsolutePath();
    astHelper = new AstHelper(registry, keyFile, this);
  }
}


