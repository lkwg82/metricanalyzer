package AstHelperTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Generics
{
  private final List<Map<String, String>> stringListMap = new ArrayList<Map<String, String>>();

  public List<Map<String, String>> getStringListMap()
  {
    return stringListMap;
  }
}
