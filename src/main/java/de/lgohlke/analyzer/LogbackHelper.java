package de.lgohlke.analyzer;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class LogbackHelper
{
  public void configure()
  {
    // assume SLF4J is bound to logback in the current environment
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

    try
    {
      JoranConfigurator configurator = new JoranConfigurator();
      configurator.setContext(lc);
      // the context was probably already configured by default configuration
      // rules
      lc.reset();
      configurator.doConfigure("src/main/resources/logback.xml");
    }
    catch (JoranException je)
    {
      // StatusPrinter will handle this
    }
    // StatusPrinter.printInCaseOfErrorsOrWarnings(lc);

  }
}
