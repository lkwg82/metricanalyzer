package de.lgohlke.qdox;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.thoughtworks.qdox.model.JavaSource;

import de.lgohlke.AST.Registry;

@Slf4j
@RequiredArgsConstructor
public class QDoxRegistryScanHandler implements IQDoxScanHandler
{
  private final Registry registry;

  @Override
  public void scanSource(final JavaSource source)
  {
    String file = source.getURL().getFile();

    if (log.isDebugEnabled())
    {
      log.debug("parsing file : " + file);
    }

    registry.getPackageName().put(file, source.getPackage() == null ? "<default>" : source.getPackage().getName());
    registry.setImports(file, source.getImports());
  }
}
