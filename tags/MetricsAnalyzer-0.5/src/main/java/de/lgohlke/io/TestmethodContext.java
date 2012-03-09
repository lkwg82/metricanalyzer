package de.lgohlke.io;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import de.lgohlke.AST.Location;
import de.lgohlke.io.bo.TestMethod;
import de.lgohlke.qdox.JavaMethodHashed;

/**
 * <p>
 * QueryResult class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@Data
@RequiredArgsConstructor
public class TestmethodContext
{
  /**
   * zu welchem Test gehört hier alles
   */
  private TestMethod                      testMethod     = null;

  /**
   * wo sind die Methoden?
   */
  private Map<JavaMethodHashed, Location> locationMap;

  /**
   * verknüpfte Methode
   */
  private Set<JavaMethodHashed>           relatedMethods = new TreeSet<JavaMethodHashed>();

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    StringBuffer b = new StringBuffer();

    b.append(testMethod == null ? null : testMethod.toString());
    b.append('\n');

    for (JavaMethodHashed m : relatedMethods)
    {
      b.append('\t');
      b.append(m.getMethod().toString());
      b.append('\n');
    }

    return b.toString();
  }
}
