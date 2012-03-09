package de.lgohlke.AST.calculators;

import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.lgohlke.AST.calculators.X.A;
import de.lgohlke.AST.calculators.X.B;
import de.lgohlke.AST.calculators.Y.Z;

/**
 * <p>TypeDistanceCalculatorTest class.</p>
 *
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class TypeDistanceCalculatorTest
{

  /**
   * <p>testJavaCore.</p>
   */
  @Test
  public void testJavaCore()
  {
    assertEquals(0D, new TypeDistanceCalculator(String.class, String.class).calculate());
    assertEquals(0D, new TypeDistanceCalculator(ArrayList.class, String.class).calculate());
    assertEquals(0D, new TypeDistanceCalculator(String.class, ArrayList.class).calculate());
    assertEquals(0D, new TypeDistanceCalculator(String.class, this.getClass()).calculate());
  }

  /**
   * <p>testNormal.</p>
   */
  @Test
  public void testNormal()
  {
    assertEquals(1D, new TypeDistanceCalculator(A.class, B.class).calculate());
    assertEquals(2D, new TypeDistanceCalculator(X.class, Z.class).calculate());
  }

  /**
   * <p>testVoid.</p>
   */
  @Test
  public void testVoid()
  {
    TypeDistanceCalculator c = new TypeDistanceCalculator("java.lang.String", "void");
    assertEquals(0D, c.calculate());
  }

  /**
   * <p>differentPathLength.</p>
   */
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
