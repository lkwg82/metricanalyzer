package AstHelperTest;

import AstHelperTest.StaticInnerClass.Metric.LEVEL;

public class StaticInnerClass
{
  static class InnerClass
  {
    Metric.LEVEL getLevel()
    {
      return LEVEL.A;
    }

    StaticInnerClass.Metric.LEVEL getLevel2()
    {
      return LEVEL.A;
    }
  }

  static class Metric
  {
    enum LEVEL
    {
      A, B
    }
  }
}
