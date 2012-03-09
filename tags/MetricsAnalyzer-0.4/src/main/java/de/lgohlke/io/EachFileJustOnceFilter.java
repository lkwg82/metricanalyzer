package de.lgohlke.io;

import java.util.Set;
import java.util.TreeSet;

import org.picocontainer.MutablePicoContainer;

import com.thoughtworks.qdox.model.JavaSource;

import de.lgohlke.qdox.IQDoxScanHandler;

public class EachFileJustOnceFilter implements IQDoxScanHandler
{
  private final IQDoxScanHandler handler;
  private final Set<String>      alreadyHandledFiles;

  /**
   * @param handler
   * @param pico
   * @param cacheKey
   */
  public EachFileJustOnceFilter(final IQDoxScanHandler handler, final MutablePicoContainer pico, final String cacheKey)
  {
    this.handler = handler;

    // get the cache / lazy init
    @SuppressWarnings("unchecked")
    Set<String> alreadyHandledFiles = (Set<String>) pico.getComponent(cacheKey);
    if (alreadyHandledFiles == null)
    {
      alreadyHandledFiles = new TreeSet<String>();
      pico.addComponent(cacheKey, alreadyHandledFiles);
    }
    this.alreadyHandledFiles = alreadyHandledFiles;
  }

  @Override
  public void scanSource(final JavaSource source)
  {
    String file = source.getURL().toString();

    // only delegate, if new
    if (alreadyHandledFiles.add(file))
    {
      handler.scanSource(source);
    }
  }
}
