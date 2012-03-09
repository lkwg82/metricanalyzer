package de.lgohlke.prototypes;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoBuilder;
import org.picocontainer.gems.monitors.Slf4jComponentMonitor;

@Slf4j
public class PicoTest
{

  @Test
  public void testFieldInjection()
  {
    MutablePicoContainer pico = new DefaultPicoContainer(new Slf4jComponentMonitor(log));
    pico.addComponent(Apple.class);
    pico.addComponent(Orange.class);

    log.debug("" + pico.hashCode());
    pico.getComponent(Apple.class);
  }

  @Test
  public void testCachedFieldInjection()
  {
    MutablePicoContainer pico = new PicoBuilder().withAnnotatedFieldInjection().withMonitor(new Slf4jComponentMonitor(log)).withCaching().build();
    pico.addComponent(Apple.class);
    pico.addComponent(Orange.class);

    log.debug("" + pico.hashCode());
    log.debug("o:" + pico.getComponent(Apple.class).getOrange().hashCode());
    log.debug("o:" + pico.getComponent(Apple.class).getOrange().hashCode());
  }

  @Test
  public void testContainerHierarchy()
  {
    MutablePicoContainer picoParent = new PicoBuilder().withMonitor(new Slf4jComponentMonitor(log)).build();
    MutablePicoContainer picoCache = new PicoBuilder(picoParent).withAnnotatedFieldInjection().withMonitor(new Slf4jComponentMonitor(log)).withCaching().build();

    picoParent.addChildContainer(picoCache);

    picoCache.addComponent(Apple.class);
    picoParent.addComponent(Orange.class);

    log.debug("parent: " +  picoParent.hashCode());
    log.debug("parent: " +  picoCache.getParent().hashCode());

    log.debug("o:" + picoCache.getComponent(Apple.class).getOrange().hashCode());
    log.debug("o:" + picoCache.getComponent(Apple.class).getOrange().hashCode());
  }
}
