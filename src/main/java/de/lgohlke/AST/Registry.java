package de.lgohlke.AST;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.NoResultException;

import lombok.Getter;

import org.sonar.squid.measures.MetricDef;

/**
 * <p>
 * Registry class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
public class Registry
{
  private final Map<String, String[]>                   imports              = new HashMap<String, String[]>();
  @Getter
  private final Map<String, String>                     packageName          = new HashMap<String, String>();
  @Getter
  private final Set<String>                             indexedFiles         = new TreeSet<String>();
  // private final Set<String> indexedPaths = Collections.synchronizedSet(new TreeSet<String>());
  // TODO visitorKey lookupTable (könnten nachher sehr viele werden)
  // TODO code ist auch redundant
  @Getter
  private final Map<VisitorKey, String[]>               code                 = new HashMap<VisitorKey, String[]>();
  // new ParallelCompressedHashMap<VisitorKey, String[]>()
  // .addValueCompressor(new StringArrayCompressor());
  private final Map<VisitorKey, Map<MetricDef, Double>> metricPerLineMap     = new HashMap<VisitorKey, Map<MetricDef, Double>>();
  private final Map<VisitorKey, Map<MetricDef, String>> description          = new HashMap<VisitorKey, Map<MetricDef, String>>();
  @Getter
  private final List<Location>                          locationList         = new ArrayList<Location>();
  @Getter
  private final Set<String>                             qdoxErrorParsedFiles = new TreeSet<String>();

  // keep in mind for scaling
  // private final Map<VisitorKey,Integer> visitorKeyLookupTable = new TreeMap<VisitorKey, Integer>();

  /**
   * <p>
   * printStats.
   * </p>
   * 
   * @return a {@link java.lang.String} object.
   */
  public String printStats()
  {
    StringBuffer b = new StringBuffer("\n");
    String format = "%20s : %8d#  %8.2fMb\n";

    b.append(String.format(format, "imports [keys]", imports.keySet().size(), computeStringSizes(imports.keySet())));
    b.append(String.format(format, "imports [values]", imports.values().size(), computeStringSizes(imports.values())));

    b.append(String.format(format, "packageName [keys]", packageName.keySet().size(), computeStringSizes(packageName.keySet())));
    b.append(String.format(format, "packageName [values]", packageName.values().size(), computeStringSizes(new HashSet<String>(packageName.values()))));

    b.append(String.format(format, "indexedFiles", indexedFiles.size(), computeStringSizes(indexedFiles)));
    // b.append(String.format(format, "indexedPaths", indexedPaths.size(), computeStringSizes(indexedPaths)));

    b.append(String.format(format, "code [keys]", code.keySet().size(), computeStringSizes(code.keySet().toArray(new VisitorKey[code.keySet().size()]))));
    b.append(String.format(format, "code [values]", code.values().size(), computeStringSizes(code.values())));

    return b.toString();
  }

  private static double computeStringSizes(final VisitorKey[] set)
  {
    String[] strArray = new String[set.length];
    for (int i = 0; i < strArray.length; i++)
    {
      strArray[i] = set[i].toString();
    }
    return computeStringSizes(strArray);
  }

  private static double computeStringSizes(final Set<String> set)
  {
    return computeStringSizes(set.toArray(new String[set.size()]));
  }

  private static double computeStringSizes(final Collection<String[]> set)
  {
    double sum = 0;
    for (String[] array : set)
    {
      sum += computeStringSizes(array);
    }
    return sum;
  }

  private static double computeStringSizes(final String[] importsArray)
  {
    double import_keys_mem = 0;
    for (int i = 0; i < importsArray.length; i++)
    {
      import_keys_mem += importsArray[i].length();
    }
    return import_keys_mem / (1024 * 1024);
  }

  /**
   * <p>
   * Getter for the field <code>imports</code>.
   * </p>
   * 
   * @param path
   *          a {@link java.lang.String} object.
   * @return an array of {@link java.lang.String} objects.
   */
  public String[] getImports(final String path)
  {
    return imports.get(path);
  }

  /**
   * <p>
   * Setter for the field <code>imports</code>.
   * </p>
   * 
   * @param file
   *          a {@link java.lang.String} object.
   * @param imports
   *          an array of {@link java.lang.String} objects.
   */
  public void setImports(final String file, final String[] imports)
  {
    this.imports.put(file, imports);
  }

  /**
   * tries to retrieve the canonical path of the class
   * <p/>
   * 
   * @param key
   *          a {@link java.lang.String} object.
   * @param typeName
   *          a {@link java.lang.String} object.
   * @return a {@link java.lang.String} object.
   */
  public String getCanonicalClassnameOfType(final String key, final String typeName)
  {
    String result = null;

    if ("void".equals(typeName))
    {
      throw new NoResultException("type is void");
    }
    else if (typeName.startsWith("java.lang."))
    {
      return typeName;
    }
    else
    {

      boolean found = false;
      for (String thisImport : imports.get(key))
      {
        if ((!found && thisImport.endsWith('.' + typeName)) || thisImport.equals(typeName))
        {
          result = thisImport;
          found = true;
        }
      }

      if (result == null)
      {
        String packageName = getPackageName().get(key);
        if (typeName.startsWith(packageName + ".") && (typeName.length() > (packageName.length() + 1)))
        {
          result = typeName;
        }
        else
        {
          try
          {
            String _package = "java.lang.";
            Class.forName(_package + typeName);
            result = _package + typeName;
          }
          catch (ClassNotFoundException e)
          {
            result = packageName + "." + typeName;
          }
        }

      }
    }

    return result;
  }

  /**
   * <p>
   * addMetricPerLine.
   * </p>
   * 
   * @param key
   *          a {@link de.lgohlke.AST.VisitorKey} object.
   * @param metric
   *          a {@link org.sonar.squid.measures.MetricDef} object.
   * @param value
   *          a {@link java.lang.Double} object.
   */
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

  /**
   * <p>
   * Getter for the field <code>metricPerLineMap</code>.
   * </p>
   * 
   * @return a {@link java.util.Map} object.
   */
  public Map<VisitorKey, Map<MetricDef, Double>> getMetricPerLineMap()
  {
    return metricPerLineMap;
  }

  /**
   * <p>
   * addDescription.
   * </p>
   * 
   * @param key
   *          a {@link de.lgohlke.AST.VisitorKey} object.
   * @param metric
   *          a {@link org.sonar.squid.measures.MetricDef} object.
   * @param describe
   *          a {@link java.lang.String} object.
   */
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

  /**
   * <p>
   * Getter for the field <code>description</code>.
   * </p>
   * 
   * @return a {@link java.util.Map} object.
   */
  public Map<VisitorKey, Map<MetricDef, String>> getDescription()
  {
    return description;
  }
}
