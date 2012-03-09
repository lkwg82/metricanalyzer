package de.lgohlke;

import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoBuilder;
import org.picocontainer.gems.monitors.Slf4jComponentMonitor;

public class PicoContainerFactory
{
  private PicoContainerFactory(){
    // ok
  }

  public static MutablePicoContainer createContainer()
  {
    return new PicoBuilder().//
        //        withAnnotatedFieldInjection().//
        withMonitor(new Slf4jComponentMonitor()).//
        withCaching().//
        withLocking().//
        build().
        // add components
        //        addComponent(SourcePathFixer.class).//
        addComponent(CommonStore.class);
  }
}
