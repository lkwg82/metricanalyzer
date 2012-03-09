package de.lgohlke.AST.calculators;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

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

  @Test
  public void testDISTANCE_RULE_FIRST()
  {
    testRule(DISTANCE_RULE.FIRST, FIRST);
  }

  @Test
  public void testDISTANCE_RULE_LOWEST()
  {
    testRule(DISTANCE_RULE.LOWEST, LOWEST);
  }

  @Test
  public void testDISTANCE_RULE_HIGHEST()
  {
    testRule(DISTANCE_RULE.HIGHEST, HIGHEST);
  }

  @Test
  public void testDISTANCE_RULE_GEOMETRIC_MEAN()
  {
    testRule(DISTANCE_RULE.GEOMETRIC_MEAN, GEOMETRIC_MEAN);
  }

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
