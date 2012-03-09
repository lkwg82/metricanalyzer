package de.lgohlke.io.bo;

public enum TEST_TYPE
{
  JUNIT
  {

    @Override
    String getImportSignature()
    {
      return "org.junit.Test";
    }

  },
  TESTNG
  {

    @Override
    String getImportSignature()
    {
      return "org.testng.annotations.Test";
    }

  };

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

  public static boolean isTestImport(final String _import)
  {
    return getType(_import) != null;
  }

  abstract String getImportSignature();

}
