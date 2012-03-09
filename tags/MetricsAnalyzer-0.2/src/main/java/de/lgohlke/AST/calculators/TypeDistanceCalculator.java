package de.lgohlke.AST.calculators;

import org.apache.commons.lang.StringUtils;

public class TypeDistanceCalculator implements ICalculator
{
  private final String[]     bPath;
  private final String[]     aPath;
  private final StringBuffer description = new StringBuffer();

  /**
   * @param a
   * @param b
   */
  @SuppressWarnings("rawtypes")
  public TypeDistanceCalculator(final Class a, final Class b)
  {
    this(a.getCanonicalName(), b.getCanonicalName());
  }

  /**
   * @param currentClass
   * @param canonicalType
   */
  public TypeDistanceCalculator(final String currentClass, final String canonicalType)
  {
    this(currentClass.split("\\."), canonicalType.split("\\."));
  }

  private TypeDistanceCalculator(final String[] split, final String[] split2)
  {
    this.aPath = split.clone();
    this.bPath = split2.clone();
  }

  /*
   * (non-Javadoc)
   * @see de.lgohlke.AST.calculators.ICalculator#calculate()
   */
  public Double calculate()
  {
    int result = 0;

    // Types from java-core
    String[] packagePaths = new String[]
    { "java." };
    boolean foundTypefromJavaCore = false;
    boolean foundClassAndCorrespondingTestClass = false;

    String aJoinedPath = StringUtils.join(aPath, '.');
    String bJoinedPath = StringUtils.join(bPath, '.');

    description.append("calculate " + this.getClass().getSimpleName() + " for : \n");
    description.append("\t" + aJoinedPath + "\n");
    description.append("\t" + bJoinedPath + "\n");
    description.append("\n");

    // test on class and corresponding test-clas
    if (aPath.length == bPath.length)
    {
      int n = aPath.length - 1;
      String aClass = aPath[n].toLowerCase();
      String bClass = bPath[n].toLowerCase();
      if (aClass.equals(bClass + "test") || bClass.equals(aClass + "test"))
      {
        foundClassAndCorrespondingTestClass = true;
        description.append("distance between class and its test is ZERO");
      }
    }

    if (!foundClassAndCorrespondingTestClass)
    {
      for (String path : packagePaths)
      {
        if (!foundTypefromJavaCore)
        {
          if (aJoinedPath.equals("void") || aJoinedPath.startsWith(path))
          {
            description.append("found java core class : " + aJoinedPath + "\n");
            foundTypefromJavaCore = true;
          }
          else if (bJoinedPath.equals("void") || bJoinedPath.startsWith(path))
          {
            description.append("found java core class : " + bJoinedPath + "\n");
            foundTypefromJavaCore = true;
          }
        }
      }

      if (foundTypefromJavaCore)
      {
        description.append("\nDistance from everywhere to a JavaCore-Class is ZERO");
        result = 0;
      }
      else
      {
        // the difference in length
        result = Math.abs(aPath.length - bPath.length);
        int min = Math.min(aPath.length, bPath.length);

        boolean startingEquals = true;
        for (int i = 0; i < min; i++)
        {
          if (startingEquals)
          {
            if (!aPath[i].equals(bPath[i]))
            {
              startingEquals = false;
              result++;
            }
          }
          else
          {
            result += 2;
          }
        }
      }
    }

    return Double.valueOf(result);
  }

  public String describe()
  {
    return description.toString();
  }
}
