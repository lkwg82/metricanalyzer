package RelatedTests;

import org.junit.After;
import org.junit.Before;

public class Super
{
  private int x = 2;

  @Before
  public void setupSuper()
  {
    System.out.println("before super");
  }

  @After
  public void afterSuper()
  {
    System.out.println("after super");
  }
}
