package com.epam.preprod.karavayev.util;

import com.epam.preprod.karavayev.dto.Constraint;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLAccessParser {

    private static final Logger LOGGER = Logger.getLogger(XMLAccessParser.class);
    private static final String CONSTRAINT_TAG = "constraint";
    private static final String URL_PATTERN_TAG = "url-pattern";
    private static final String ROLE_TAG = "role";

    public List<Constraint> parseToConstraints(String path) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(path));
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName(CONSTRAINT_TAG);
            List<Constraint> constraints = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                constraints.add(getConstraint(nodeList.item(i)));
            }
            return constraints;
        } catch (SAXException | ParserConfigurationException | IOException e) {
            LOGGER.error(e);
            throw new IllegalArgumentException("Cannot parse xml from file -> " + path);
        }
    }

    private Constraint getConstraint(Node node) {
        Constraint constraint = new Constraint();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            constraint.setPattern(getTagValue(URL_PATTERN_TAG, element));
            constraint.setRoles(getTagValues(ROLE_TAG, element));
        }

        return constraint;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        LOGGER.debug(nodeList);
        return nodeList.item(0).getNodeValue();
    }

    private List<String> getTagValues(String tag, Element element) {
        LOGGER.debug(element);
        LOGGER.debug(element.getElementsByTagName(tag).item(0));
        NodeList roleNodes = element.getElementsByTagName(tag);
        List<String> tagValues = new ArrayList<>();
        for (int i = 0; i < roleNodes.getLength(); i++) {
            Node tempNode = element.getElementsByTagName(tag).item(i).getChildNodes().item(0);
            tagValues.add(tempNode.getNodeValue());
        }
        return tagValues;
    }

}
