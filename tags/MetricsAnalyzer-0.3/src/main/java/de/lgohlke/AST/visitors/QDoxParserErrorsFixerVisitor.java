package de.lgohlke.AST.visitors;

import java.io.File;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.sonar.java.ast.visitor.JavaAstVisitor;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import de.lgohlke.AST.AstHelper;
import de.lgohlke.AST.Registry;

@RequiredArgsConstructor
@Slf4j
public class QDoxParserErrorsFixerVisitor extends JavaAstVisitor
{
  private final Registry registry;

  @Override
  public void visitFile(final DetailAST ast)
  {
    File file = new File(getFileContents().getFilename());
    String keyFile = file.getAbsolutePath();

    // do we need to fix?
    if (registry.getQdoxErrorParsedFiles().contains(keyFile))
    {
      AstHelper astHelper = new AstHelper(registry, keyFile, this);

      // need to fix with ast execution
      String packageName = astHelper.getPackageName(ast);

      List<String> imports = astHelper.getImports(ast);

      registry.getPackageName().put(keyFile, packageName == null ? "<default>" : packageName);
      registry.setImports(keyFile, imports.toArray(new String[imports.size()]));
    }
  }
}
