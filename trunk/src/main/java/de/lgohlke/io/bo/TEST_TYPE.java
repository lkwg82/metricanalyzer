package de.lgohlke.io.bo;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;

/**
 * <p>
 * TEST_TYPE class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
public enum TEST_TYPE
{
  JUNIT
  {
    @Override
    public String getImportSignature()
    {
      return "org.junit.Test";
    }

    @Override
    public List<String> getLifeCycleAnnotations()
    {
      return Arrays.asList(new String[] { "org.junit.Before", "org.junit.After","org.junit.BeforeClass","org.junit.AfterClass" });
    }

  },
  TESTNG
  {

    @Override
    public String getImportSignature()
    {
      return "org.testng.annotations.Test";
    }

    @Override
    public List<String> getLifeCycleAnnotations()
    {
      return Arrays.asList(new String[] { "org.testng.annotations.BeforeSuite", "org.testng.annotations.AfterSuite", "org.testng.annotations.BeforeTest",
          "org.testng.annotations.AfterTest", "org.testng.annotations.BeforeGroups", "org.testng.annotations.AfterGroups",
          "org.testng.annotations.BeforeClass", "org.testng.annotations.AfterClass", "org.testng.annotations.BeforeMethod",
      "org.testng.annotations.AfterMethod" });
    }

  },
  NONE
  {
    @Override
    public String getImportSignature()
    {
      return "";
    }

    @Override
    public List<String> getLifeCycleAnnotations()
    {
      throw new NotImplementedException();
    }
  };

  /**
   * <p>
   * getType.
   * </p>
   * 
   * @param type
   *          a {@link java.lang.String} object.
   * @return a {@link de.lgohlke.io.bo.TEST_TYPE} object.
   */
  public static TEST_TYPE getType(final String type)
  {
    for (TEST_TYPE t : TEST_TYPE.values())
    {
      String signature = t.getImportSignature();
      if (signature.equals(type))
      {
        return t;
      }
    }
    return null;
  }

  /**
   * <p>
   * isTestImport.
   * </p>
   * 
   * @param _import
   *          a {@link java.lang.String} object.
   * @return a boolean.
   */
  public static boolean isTestImport(final String _import)
  {
    return getType(_import) != null;
  }

  /**
   * <p>
   * detectType.
   * </p>
   * 
   * @param _import
   *          a {@link java.lang.String} object.
   * @return a {@link de.lgohlke.io.bo.TEST_TYPE} object.
   */
  public static TEST_TYPE detectType(final String _import)
  {
    return getType(_import);
  }

  /**
   * <p>
   * getImportSignature.
   * </p>
   * 
   * @return a {@link java.lang.String} object.
   */
  public abstract String getImportSignature();

  /**
   * <p>
   * getLifeCycleAnnotations.
   * </p>
   * 
   * @return a {@link java.util.List} object.
   */
  public abstract List<String> getLifeCycleAnnotations();

}
