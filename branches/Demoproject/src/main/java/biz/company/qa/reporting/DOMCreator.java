package biz.company.qa.reporting;

import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class helps to create a DOM-tree out of all tests
 * 
 
 */
public class DOMCreator {

    /**
     * Returns a DOM-Document object out of all the Tests
     */
    public Document createDocument(Tests tests) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.newDocument();

        Element root = document.createElement("tests");
        document.appendChild(root);

        Iterator<Test> iterator = tests.iterator();
        while (iterator.hasNext()) {
            Element test = createTestElement(document, iterator.next());
            root.appendChild(test);
        }

        return document;
    }

    /**
     * Private method to create an XML Element for each Test object individually
     */
    private Element createTestElement(Document document, Test test) {
        Element node = document.createElement("test");
        node.setAttribute("id", test.getId());
        node.setAttribute("name", test.getName());
        node.setAttribute("classname", test.getClassname());

        Element date = document.createElement("date");
        date.appendChild(document.createTextNode(test.getDate()));
        node.appendChild(date);

        Element result = document.createElement("result");
        result.appendChild(document.createTextNode(test.getResult()));
        node.appendChild(result);

        Element notes = document.createElement("notes");
        notes.appendChild(document.createTextNode(test.getNotes()));
        node.appendChild(notes);

        Element requirements = document.createElement("requirements");
        for (String text : test.getRequirements()) {
            Element requirement = document.createElement("requirement");
            requirement.appendChild(document.createTextNode(text));
            requirements.appendChild(requirement);
        }
        node.appendChild(requirements);

        Element bugs = document.createElement("bugs");
        for (String text : test.getBugs()) {
            Element bug = document.createElement("bug");
            bug.appendChild(document.createTextNode(text));
            bugs.appendChild(bug);
        }
        node.appendChild(bugs);

        return node;
    }
}