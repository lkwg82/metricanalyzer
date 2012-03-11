package de.lgohlke.AST.calculators;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

/**
 * <p>DISTANCE_RULETest class.</p>
 *
 * @author lars
 * @version $Id: $
 * @since 0.3
 */
public class DISTANCE_RULETest
{
  private final String     currentClass    = "de.lgohlke.test.Test";

  private final static int FIRST           = 0;
  private final static int LOWEST          = 0;
  private final static int HIGHEST         = 2;
  private final static int ARITHMETIC_MEAN = 1;
  private final static int GEOMETRIC_MEAN  = 1;
  private final String[]   typeSet         = new String[]
                                           { "java.lang.String", "org.apache.TestClass", "org.apache.commons.math.stat.descriptive" };

  /**
   * <p>testDISTANCE_RULE_FIRST.</p>
   */
  @Test
  public void testDISTANCE_RULE_FIRST()
  {
    testRule(DISTANCE_RULE.FIRST, FIRST);
  }

  /**
   * <p>testDISTANCE_RULE_LOWEST.</p>
   */
  @Test
  public void testDISTANCE_RULE_LOWEST()
  {
    testRule(DISTANCE_RULE.LOWEST, LOWEST);
  }

  /**
   * <p>testDISTANCE_RULE_HIGHEST.</p>
   */
  @Test
  public void testDISTANCE_RULE_HIGHEST()
  {
    testRule(DISTANCE_RULE.HIGHEST, HIGHEST);
  }

  /**
   * <p>testDISTANCE_RULE_GEOMETRIC_MEAN.</p>
   */
  @Test
  public void testDISTANCE_RULE_GEOMETRIC_MEAN()
  {
    testRule(DISTANCE_RULE.GEOMETRIC_MEAN, GEOMETRIC_MEAN);
  }

  /**
   * <p>testDISTANCE_RULE_ARITHMETRIC_MEAN.</p>
   */
  @Test
  public void testDISTANCE_RULE_ARITHMETRIC_MEAN()
  {
    testRule(DISTANCE_RULE.ARITHMETIC_MEAN, ARITHMETIC_MEAN);
  }

  private void testRule(final DISTANCE_RULE rule, final int index)
  {

    String classname = rule.chooseAst(currentClass, Arrays.asList(typeSet));
    Assert.assertEquals(typeSet[index], classname);
  }
}
