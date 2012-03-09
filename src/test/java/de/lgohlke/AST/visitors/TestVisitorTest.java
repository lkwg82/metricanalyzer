package de.lgohlke.AST.visitors;

import java.io.File;

import org.junit.Test;
import org.sonar.java.ast.JavaAstScanner;
import org.sonar.java.squid.JavaSquidConfiguration;
import org.sonar.squid.Squid;
import org.sonar.squid.measures.Metric;

import de.lgohlke.AST.ASTMetrics;

/**
 * <p>TestVisitorTest class.</p>
 *
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class TestVisitorTest
{
  /**
   * <p>test.</p>
   *
   * @throws java.lang.SecurityException if any.
   * @throws java.lang.IllegalArgumentException if any.
   * @throws java.lang.NoSuchFieldException if any.
   * @throws java.lang.IllegalAccessException if any.
   */
  @Test
  public void test() throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException
  {
    Squid squid = new Squid(new JavaSquidConfiguration(false));

    // Registry registry = new Registry();

    // squid.registerVisitor(new MethodVisitor(registry));
    squid.register(JavaAstScanner.class).scanDirectory(new File("src/main/java"));
    // SourceProject project =
    squid.decorateSourceCodeTreeWith(Metric.COMPLEXITY, ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION);

    // MutablePicoContainer pico = new DefaultPicoContainer();
    // pico.addComponent("singleton", squid);
    // new ProjectPrinter(project, pico).print();
  }
}
