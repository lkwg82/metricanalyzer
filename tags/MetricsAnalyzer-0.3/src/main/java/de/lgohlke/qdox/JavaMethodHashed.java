package de.lgohlke.qdox;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import com.thoughtworks.qdox.model.JavaMethod;

@EqualsAndHashCode
@ToString
public class JavaMethodHashed
{
  @Getter
  private final JavaMethod method;

  private final String     signature;
  private final String     clazz;

  public JavaMethodHashed(final JavaMethod method)
  {
    this.method = method;
    this.signature = method.getDeclarationSignature(true);
    this.clazz = method.getParentClass().getFullyQualifiedName();
  }
}
