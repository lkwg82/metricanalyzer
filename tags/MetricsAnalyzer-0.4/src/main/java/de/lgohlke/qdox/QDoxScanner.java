package de.lgohlke.qdox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.thoughtworks.qdox.model.JavaSource;

import de.lgohlke.AST.Registry;

/**
 * <p>
 * QDoxScanner class.
 * </p>
 * 
 * @author lars
 * @version $Id: $
 */
@Slf4j
@RequiredArgsConstructor
public class QDoxScanner
{
  private final Registry               registry;
  private final List<IQDoxScanHandler> scanHandlerList = new ArrayList<IQDoxScanHandler>();

  private JavaDocBuilderFascade        docBuilder;

  public QDoxScanner addScanHandler(final IQDoxScanHandler handler)
  {
    docBuilder = new JavaDocBuilderFascade(registry);
    scanHandlerList.add(handler);
    return this;
  }

  /**
   * <p>
   * scan.
   * </p>
   * 
   * @throws java.io.IOException
   *           if any.
   */
  public void scan(final File file) throws IOException
  {
    log.debug("scanning " + file);
    if (file.isDirectory())
    {
      docBuilder.addSourceTree(file);
    }
    else
    {
      docBuilder.addSource(file);
    }

    for (JavaSource source : docBuilder.getSources())
    {
      for (IQDoxScanHandler handler : scanHandlerList)
      {
        handler.scanSource(source);
      }
    }
  }

}
