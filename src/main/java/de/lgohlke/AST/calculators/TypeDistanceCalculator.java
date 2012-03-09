package de.lgohlke.AST.calculators;

import org.apache.commons.lang.StringUtils;

/**
 * <p>TypeDistanceCalculator class.</p>
 *
 * @author lars
 * @version $Id: $
 */
public class TypeDistanceCalculator implements ICalculator
{
  // Types from java-core
  private final static String[] packagePaths = new String[] { "java." };

  private final String[]        bPath;
  private final String[]        aPath;
  private final StringBuffer    description  = new StringBuffer();

  /**
   * <p>Constructor for TypeDistanceCalculator.</p>
   *
   * @param a a {@link java.lang.Class} object.
   * @param b a {@link java.lang.Class} object.
   */
  @SuppressWarnings("rawtypes")
  public TypeDistanceCalculator(final Class a, final Class b)
  {
    this(a.getCanonicalName(), b.getCanonicalName());
  }

  /**
   * <p>Constructor for TypeDistanceCalculator.</p>
   *
   * @param currentClass a {@link java.lang.String} object.
   * @param canonicalType a {@link java.lang.String} object.
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
  @Override
  public Double calculate()
  {
    int result = 0;

    boolean foundTypefromJavaCore = false;
    boolean foundClassAndCorrespondingTestClass = false;

    String aJoinedPath = StringUtils.join(aPath, '.');
    String bJoinedPath = StringUtils.join(bPath, '.');

    description.append("calculate " + this.getClass().getSimpleName() + " for : \n");
    description.append("\t" + aJoinedPath + "\n");
    description.append("\t" + bJoinedPath + "\n");
    description.append("\n");

    // test on class and corresponding test-class
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
          if ("void".equals(aJoinedPath) || aJoinedPath.startsWith(path))
          {
            description.append("found java core class : " + aJoinedPath + "\n");
            foundTypefromJavaCore = true;
          }
          else if ("void".equals(bJoinedPath) || bJoinedPath.startsWith(path))
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

  /**
   * <p>describe.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  @Override
  public String describe()
  {
    return description.toString();
  }
}
