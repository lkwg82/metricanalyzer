package de.lgohlke.AST.calculators;

import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.sonar.core.plugin.AbstractPluginRepositoryTest.A;
import org.sonar.core.plugin.AbstractPluginRepositoryTest.B;

import de.lgohlke.AST.calculators.Y.Z;

public class TypeDistanceCalculatorTest
{

  @Test
  public void testJavaCore()
  {
    assertEquals(0D, new TypeDistanceCalculator(String.class, String.class).calculate());
    assertEquals(0D, new TypeDistanceCalculator(ArrayList.class, String.class).calculate());
    assertEquals(0D, new TypeDistanceCalculator(String.class, ArrayList.class).calculate());
    assertEquals(0D, new TypeDistanceCalculator(String.class, this.getClass()).calculate());
  }

  @Test
  public void testNormal()
  {
    assertEquals(1D, new TypeDistanceCalculator(A.class, B.class).calculate());
    assertEquals(2D, new TypeDistanceCalculator(X.class, Z.class).calculate());
  }

  @Test
  public void testVoid()
  {
    TypeDistanceCalculator c = new TypeDistanceCalculator("java.lang.String", "void");
    assertEquals(0D, c.calculate());
  }

  @Test
  public void differentPathLength()
  {
    String a = "com.hello2morrow.sonarplugin.AlertDecorator.AlertThreshold";
    String b = "com.hello2morrow.sonarplugin";

    TypeDistanceCalculator c = new TypeDistanceCalculator(a, b);
    assertEquals(2D, c.calculate());
  }
}

class Y
{
  class Z extends X
  {

  }
}

class X
{
  class A
  {

  }

  class B
  {

  }
}
