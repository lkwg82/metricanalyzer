package RelatedTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTestClass extends Super
{
  private int x = 2;

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
