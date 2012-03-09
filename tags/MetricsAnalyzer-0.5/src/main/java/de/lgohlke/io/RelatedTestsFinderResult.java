package de.lgohlke.io;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import com.thoughtworks.qdox.model.JavaMethod;

import de.lgohlke.AST.Location;
import de.lgohlke.io.bo.TestMethod;
import de.lgohlke.qdox.JavaMethodHashed;

/**
 * <p>RelatedTestsFinderResult class.</p>
 *
 * @author lars
 * @version $Id: $
 */
@RequiredArgsConstructor
@Data
public class RelatedTestsFinderResult
{
  private final Map<TestMethod, Set<JavaMethodHashed>> testCodeMap;
  private final Map<Location, JavaMethod>        locationJavaMethodMap;


  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    StringBuffer b = new StringBuffer();

    for (TestMethod test : testCodeMap.keySet())
    {
      b.append(test.toString());
      b.append('\n');

      for (JavaMethodHashed m : testCodeMap.get(test))
      {
        b.append('\t');
        b.append(m.toString());
        b.append('\n');

        for (Entry<Location, JavaMethod> entry : locationJavaMethodMap.entrySet())
        {
          if (entry.getValue().equals(m.getMethod()))
          {
            b.append("\t # ");
            b.append(entry.getKey().getKey());
            b.append('\n');
          }
        }
      }
    }
    return b.toString();
  }
}
