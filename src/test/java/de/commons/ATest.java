package de.commons;

import org.junit.Assert;
import org.testng.annotations.Test;

import de.utils.Helper;

public class ATest
{
  @Test
  public void simple()
  {
    A a = new A();
    String name = "name";
    Assert.assertEquals(a, a);
    Assert.assertFalse(a.equals(name));
  }

  @Test
  public void withB()
  {
    A a = new A();
    B b = new B();
    Assert.assertFalse(a.equals(b));
  }

  @Test
  public void withBandHelper()
  {
    A a = new A();
    B b = new B();
    Helper helper = new Helper();
    helper.doSthWith(a, b);
    Assert.assertFalse(a.equals(b));
  }
}
