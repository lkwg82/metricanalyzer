/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.selenium;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import biz.company.qa.common.CommonXPaths;

import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.server.SeleniumServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.selenium.Selenium;
/**
 
 */
public class SeleniumLocalServerBackend extends SeleniumControllerBackendAbstract {
  private final static Logger log = LoggerFactory.getLogger(SeleniumLocalServerBackend.class);

  protected SeleniumServer server;

  /*
   * (non-Javadoc)
   * 
   * @see biz.company.qa.infrastrastructure.SeleniumControllerBackend#start()
   */
  @Override
  public void start() throws Exception {
    log.info("server started");



    selenium = mock(Selenium.class);

    when(selenium.getLocation()).thenReturn("http://localhost:8080/");
    when(selenium.isElementPresent(CommonXPaths.TOP_LANG)).thenReturn(true);
    selenium.start();
    log.info("selenium created");
  }

  /**
   * when many instances starting concurrently, we need a central synchronized point
   * <p/>
   * the background is, that, when the {@see PortProber} tries to find afree port and this port is not just used,
   * another {@see PortProber} could find the same port, this results in an
   * 
   * <pre>
   * java.net.BindException: Address already in use
   * </pre>
   * 
   * @return
   * @throws InterruptedException
   */
  private static synchronized int findFreePort() throws InterruptedException {
    Thread.sleep(5 * 1000);
    int port = PortProber.findFreePort();
    return port;
  }

  /*
   * (non-Javadoc)
   * 
   * @see biz.company.qa.infrastrastructure.SeleniumControllerBackend#stop()
   */
  @Override
  public void stop() {
    if (selenium != null) {
      selenium.stop();
    }
  }

}
