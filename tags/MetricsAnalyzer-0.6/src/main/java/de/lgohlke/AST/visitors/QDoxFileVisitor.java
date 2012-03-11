package de.lgohlke.AST.visitors;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.java.ast.visitor.JavaAstVisitor;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import de.lgohlke.AST.Registry;
import de.lgohlke.qdox.QDoxRegistryScanHandler;
import de.lgohlke.qdox.QDoxScanner;

/**
 * parsing the files and fills the registry to enable lookups
 * 
 * @author lars
 * @version $Id: $
 */
public class QDoxFileVisitor extends JavaAstVisitor
{
  private static final Logger     log = LoggerFactory.getLogger(QDoxFileVisitor.class);
  private QDoxScanner             scanner;

  /**
   * <p>
   * Constructor for QDoxFileVisitor.
   * </p>
   * 
   * @param registry
   *          a {@link de.lgohlke.AST.Registry} object.
   */
  public QDoxFileVisitor(final Registry registry)
  {
    log.debug("new instance");
    scanner = new QDoxScanner(registry).addScanHandler(new QDoxRegistryScanHandler(registry));
  }

  /** {@inheritDoc} */
  @Override
  public void visitFile(final DetailAST ast)
  {
    try
    {
      String filename = getFileContents().getFilename();
      scanner.scan(new File(filename));
    }
    catch (IOException e)
    {
      log.error(e.getMessage(), e);
    }
  }
}
