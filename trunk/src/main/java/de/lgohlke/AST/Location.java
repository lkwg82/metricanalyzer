package de.lgohlke.AST;

import lombok.Data;

/**
 * location of an ast element
 *
 * @author lars
 * @version $Id: $
 */
@Data
public class Location
{
  private String file;
  private String clazz;
  private String methodSignature;
  private String key;
  private int    lineStart;
  private int    lineEnd;

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    StringBuffer b = new StringBuffer();

    b.append(file);
    b.append('|');
    b.append(clazz);
    b.append('|');
    b.append(key);
    b.append('|');
    b.append(lineStart);
    b.append('-');
    b.append(lineEnd);

    return b.toString();
  }
}
