package biz.company.qa.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import biz.company.qa.reporting.DOMCreator;
import biz.company.qa.reporting.Tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.TestRunner;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.w3c.dom.Document;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

@Listeners({ ResultListener.class })
public class TestNGTests {
    private final static Logger log = LoggerFactory.getLogger(TestNGTests.class);

    /**
     * Attach a listener to react when a test fails
     */
    @BeforeMethod(alwaysRun = true)
    public void attachListener(ITestContext c) {
        final TestRunner tr = (TestRunner) c;
        Set<Class<? extends ITestListener>> testListeners = new HashSet<Class<? extends ITestListener>>();
        for (ITestListener l : tr.getTestListeners()) {
            testListeners.add(l.getClass());
        }

        if (testListeners.add(ResultListener.class)) {
            ResultListener listener = new ResultListener();
            tr.addTestListener(listener);
        }
    }

    /**
     * Writes the test report as XML into a file
     */
    @AfterSuite(alwaysRun = true)
    public void writeXMLOutput() throws ParserConfigurationException, IOException {
        String name = "target/report.xml";
        Tests tests = Tests.getInstance();
        File file = new File(name);
        FileOutputStream output = new FileOutputStream(file);

        DOMCreator dom = new DOMCreator();
        Document document = dom.createDocument(tests);

        OutputFormat format = new OutputFormat(document, "UTF-8", true);
        XMLSerializer serializer = new XMLSerializer(output, format);

        log.info("Writing XML output to " + name + "...");
        serializer.serialize(document.getDocumentElement());
        log.info("done.");
    }
}
