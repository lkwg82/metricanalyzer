/*
 * (C) 2001 - 2011 company.biz AG
 */
package biz.company.qa.infrastrastructure.selenium;

import biz.company.qa.pageobjects.CONFIG_KEYS;
import biz.company.qa.pageobjects.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.selenium.Selenium;

/**
 
 */
public class SeleniumController {

  public static class NotVersionForGridConfiguredException extends Exception {
    private static final long serialVersionUID = 8186509476013096266L;
  }

  private final static Logger log = LoggerFactory.getLogger(SeleniumController.class);
  private final SeleniumControllerBackend backend;
  private boolean configuredGridVersion = false;
  private boolean use_grid_1 = true;

  /**
   * @param useWebdriver
   * @param useLocal
   */
  public SeleniumController() {

    backend = new SeleniumLocalServerBackend();
  }

  public SeleniumController useGrid1() {
    use_grid_1 = true;
    configuredGridVersion = true;
    return this;
  }

  public SeleniumController useGrid2() {
    use_grid_1 = false;
    configuredGridVersion = true;
    return this;
  }

  public SeleniumController useLocalWebdriver() {
    use_grid_1 = false;
    configuredGridVersion = true;
    return this;
  }

  public void start() throws Exception {

    //    if (configuredGridVersion)
    {
      log.info("starting with " + backend);
      backend.start();
    }
    //  else {
    //      throw new NotVersionForGridConfiguredException();
    //    }
  }

  public void stop() {
    backend.stop();
  }

  public Selenium getSelenium() {
    return backend.getSelenium();
  }

  /**
   * @param configuration
   * @return
   */
  public SeleniumController configure(final Configuration<CONFIG_KEYS> configuration) {
    backend.configure(configuration);
    return this;
  }

  /**
   *
   */
  public void init() {
    backend.init();
  }

}
