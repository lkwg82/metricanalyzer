package de.lgohlke.prototypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.picocontainer.annotations.Inject;

@RequiredArgsConstructor
public class Apple
{
  @Getter
  @Inject
  private Orange orange;
}