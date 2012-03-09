package de.lgohlke.AST.visitors;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.java.ast.visitor.JavaAstVisitor;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import de.lgohlke.AST.Registry;
import de.lgohlke.qdox.QDoxContext;

/**
 * parsing the files and fills the registry to enable lookups
 * 
 * @author lars
 */
public class QDoxFileVisitor extends JavaAstVisitor
{
  private static final Logger log = LoggerFactory.getLogger(QDoxFileVisitor.class);
  private final QDoxContext   context;

  public QDoxFileVisitor(final Registry registry)
  {
    log.debug("new instance");
    context = new QDoxContext(registry);
  }

  @Override
  public void visitFile(final DetailAST ast)
  {
    try
    {
      context.parse(getFileContents().getFilename());
    }
    catch (FileNotFoundException e)
    {
      log.error(e.getMessage(), e);
    }
    catch (IOException e)
    {
      log.error(e.getMessage(), e);
    }
  }
}
