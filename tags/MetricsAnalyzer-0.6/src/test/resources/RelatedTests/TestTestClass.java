package RelatedTests;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoBuilder;
import org.sonar.squid.Squid;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.Registry;

public class TestTestClass extends Super
{
  private Squid                      squid    = new Squid();
  private Registry                   registry = new Registry();
  private final MutablePicoContainer pico     = new PicoBuilder();
  private int                        x        = 2;

  @Before
  public void setup2()
  {
    int x = 2;
    far.packag.Type type = new Type();
    System.out.println("before 2");
  }

  @Before
  public void setup1()
  {
    int x = 2;
    far.packag.Type type = new Type();

    System.out.println("before 1");
  }

  @Test
  public void test()
  {
    MetricDef m = new MetricDef();
    // test
    int x = 2;
    far.packag.Type type = new Type();
    System.out.println("main");
  }

  @After
  public void after()
  {
    int x = 2;
    System.out.println("after");
  }
}
