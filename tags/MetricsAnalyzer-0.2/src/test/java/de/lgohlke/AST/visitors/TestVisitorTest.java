package de.lgohlke.AST.visitors;

import java.io.File;

import org.junit.Test;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.sonar.java.ast.JavaAstScanner;
import org.sonar.java.squid.JavaSquidConfiguration;
import org.sonar.squid.Squid;
import org.sonar.squid.measures.Metric;

import de.lgohlke.AST.ASTMetrics;

public class TestVisitorTest
{
  @Test
  public void test() throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException
  {
    MutablePicoContainer pico = new DefaultPicoContainer();

    Squid squid = new Squid(new JavaSquidConfiguration(false));
    pico.addComponent("singleton", squid);

    // Registry registry = new Registry();

    // squid.registerVisitor(new MethodVisitor(registry));
    squid.register(JavaAstScanner.class).scanDirectory(new File("src"));
    // SourceProject project =
    squid.decorateSourceCodeTreeWith(Metric.COMPLEXITY, ASTMetrics.AST_NUMBER_OF_VARIABLE_DEFINITION);

    // new ProjectPrinter(project, pico).print();
  }
}
