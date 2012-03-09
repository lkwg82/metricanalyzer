package de.lgohlke.io.bo;

import lombok.Data;

import com.thoughtworks.qdox.model.JavaMethod;

/**
 * composite class for {@link com.thoughtworks.qdox.model.JavaMethod} and {@link TEST_TYPE}
 * 
 * @author lars
 * @version $Id: $
 */
@Data
public class TestMethod
{
  private final JavaMethod method;
  private TEST_TYPE        type;
  private final String     signature;
  private final String     clazz;

  public TestMethod(final JavaMethod method)
  {
    this.method = method;
    this.signature = method.getDeclarationSignature(true);
    this.clazz = method.getParentClass().getFullyQualifiedName();
  }

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    StringBuffer buffer = new StringBuffer();
    buffer.append(method.getParentClass().getFullyQualifiedName());
    buffer.append('.');
    buffer.append(method.getCallSignature());
    buffer.append("[");
    buffer.append(type);
    buffer.append("]");
    return buffer.toString();
  }
}
