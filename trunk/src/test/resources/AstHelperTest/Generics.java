package AstHelperTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.File;

public class Generics
{
  private File[] file;
  private final List<Map<String, String>> stringListMap = new ArrayList<Map<String, String>>();

  public List<Map<String, String>> getStringListMap()
  {
    return stringListMap;
  }
}
