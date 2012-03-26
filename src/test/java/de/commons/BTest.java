package de.commons;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.utils.Helper;

abstract class TestCase{
  protected Helper helper;
}

public class BTest extends TestCase
{
  private A a = new A();
  private B b;

  @BeforeMethod
  public void beforeMethod()
  {
    b = new B();
  }

  @Test
  public void simple()
  {
    String name = "name";
    Assert.assertEquals(this, this);
    Assert.assertFalse(a.equals(name));
  }
}
