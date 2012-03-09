package de.lgohlke.AST;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.NoResultException;

import org.sonar.squid.measures.MetricDef;

import de.lgohlke.AST.visitors.VisitorKey;

public class Registry
{
  private final Map<String, String[]>                   imports          = new HashMap<String, String[]>();
  private final Map<String, String>                     packageName      = new HashMap<String, String>();
  private final Set<String>                             indexedFiles     = new TreeSet<String>();
  private final Set<String>                             indexedPaths     = new TreeSet<String>();
  // TODO visitorKey lookupTable (könnten nachher sehr viele werden)
  // TODO code ist auch redundant
  private final Map<VisitorKey, String[]>               codeMap          = new HashMap<VisitorKey, String[]>();
  private final Map<VisitorKey, Map<MetricDef, Double>> metricPerLineMap = new HashMap<VisitorKey, Map<MetricDef, Double>>();
  private final Map<VisitorKey, Map<MetricDef, String>> description      = new HashMap<VisitorKey, Map<MetricDef, String>>();

  // keep in mind for scaling
  // private final Map<VisitorKey,Integer> visitorKeyLookupTable = new TreeMap<VisitorKey, Integer>();

  public String[] getImports(final String path)
  {
    return imports.get(path);
  }

  public Set<String> getIndexedFiles()
  {
    return indexedFiles;
  }

  public boolean isIndexed(final String path)
  {
    return getIndexedPaths().contains(path);
  }

  public void setImports(final String file, final String[] imports)
  {
    this.imports.put(file, imports);
  }

  public Set<String> getIndexedPaths()
  {
    return indexedPaths;
  }

  public Map<String, String> getPackageName()
  {
    return packageName;
  }

  /**
   * tries to retrieve the canonical path of the class
   * <p/>
   * 
   * @param key
   * @param typeName
   * @return
   */
  public String getCanonicalClassnameOfType(final String key, final String typeName)
  {
    String result = null;

    if (typeName.equals("void"))
    {
      throw new NoResultException("type is void");
    }
    else
    {

      boolean found = false;
      for (String thisImport : imports.get(key))
      {
        if (!found && thisImport.matches(".*\\." + typeName))
        {
          result = thisImport;
          found = true;
        }
      }

      if (result == null)
      {
        try
        {
          String _package = "java.lang.";
          Class.forName(_package + typeName);
          result = _package + typeName;
        }
        catch (ClassNotFoundException e)
        {
          result = getPackageName().get(key) + "." + typeName;
        }

      }
    }

    return result;
  }

  public Map<VisitorKey, String[]> getCode()
  {
    return codeMap;
  }

  public void addMetricPerLine(final VisitorKey key, final MetricDef metric, final Double value)
  {
    // if (value > 0D)
    {

      if (!getMetricPerLineMap().containsKey(key))
      {
        getMetricPerLineMap().put(key, new HashMap<MetricDef, Double>());
      }

      if (!getMetricPerLineMap().get(key).containsKey(metric))
      {
        getMetricPerLineMap().get(key).put(metric, value);
      }
      else
      {
        getMetricPerLineMap().get(key).put(metric, value + getMetricPerLineMap().get(key).get(metric));
      }
    }
  }

  public Map<VisitorKey, Map<MetricDef, Double>> getMetricPerLineMap()
  {
    return metricPerLineMap;
  }

  public void addDescription(final VisitorKey key, final MetricDef metric, final String describe)
  {
    if (!getDescription().containsKey(key))
    {
      getDescription().put(key, new HashMap<MetricDef, String>());
    }

    if (!getDescription().get(key).containsKey(metric))
    {
      getDescription().get(key).put(metric, describe);
    }
    else
    {
      getDescription().get(key).put(metric, describe + "\n\n" + getDescription().get(key).get(metric));
    }
  }

  public Map<VisitorKey, Map<MetricDef, String>> getDescription()
  {
    return description;
  }

}
