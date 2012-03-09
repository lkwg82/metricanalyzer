package de.lgohlke.io.bo;

import com.thoughtworks.qdox.model.JavaMethod;

public class TestMethod
{

  private JavaMethod method;
  private TEST_TYPE  type;

  public TestMethod(final JavaMethod method)
  {
    this.setMethod(method);
  }

  public final void setMethod(final JavaMethod method)
  {
    this.method = method;
  }

  public JavaMethod getMethod()
  {
    return method;
  }

  @Override
  public String toString()
  {
    StringBuffer buffer = new StringBuffer();
    buffer.append(method.getCallSignature());
    buffer.append("[");
    buffer.append(type);
    buffer.append("]");
    return buffer.toString();
  }

  public void setType(final TEST_TYPE type)
  {
    this.type = type;
  }

  public TEST_TYPE getType()
  {
    return type;
  }
}
