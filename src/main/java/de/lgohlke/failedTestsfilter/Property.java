package de.lgohlke.failedTestsfilter;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Data
@RequiredArgsConstructor
@XStreamAlias("property")
public class Property{
  @XStreamAsAttribute
  private final String name;
  @XStreamAsAttribute
  private final String value;
}