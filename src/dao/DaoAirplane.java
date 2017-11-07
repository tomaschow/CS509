package dao;

import beans.Airplane;
import beans.Airplanes;
import org.w3c.dom.CharacterData;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by: Tomas on 2017/11/03.
 */
public class DaoAirplane {
    public static Airplanes addAll (String xmlAirplanes) throws NullPointerException {
        Airplanes airplanes = new Airplanes();

        // Load the XML string into a DOM tree for ease of processing
        // then iterate over all nodes adding each airport to our collection
        Document docAirplanes = buildDomDoc (xmlAirplanes);
        assert docAirplanes != null;
        NodeList nodesAirplanes = docAirplanes.getElementsByTagName("Airplane");

        for (int i = 0; i < nodesAirplanes.getLength(); i++) {
            Element elementAirplane = (Element) nodesAirplanes.item(i);
            Airplane airplane = buildAirplane (elementAirplane);

            if (airplane.isValid()) {
                airplanes.add(airplane);
            }
        }

        return airplanes;
    }

    static private Airplane buildAirplane(Node nodeAirplane) {
        Airplane airplane = new Airplane();

        String manufacturer;
        String name;
        int maxFirst;
        int maxCoach;

        // The manufacturer and model are attributes
        Element elementAirplane = (Element) nodeAirplane;
        manufacturer = elementAirplane.getAttributeNode("Manufacturer").getValue();
        name = elementAirplane.getAttributeNode("Model").getValue();

        // The seats are child elements
        Element elementSeat;
        elementSeat = (Element)elementAirplane.getElementsByTagName("FirstClassSeats").item(0);
        maxFirst = Integer.parseInt(getCharacterDataFromElement(elementSeat));

        elementSeat = (Element)elementAirplane.getElementsByTagName("CoachSeats").item(0);
        maxCoach = Integer.parseInt(getCharacterDataFromElement(elementSeat));

        airplane.setName(name);
        airplane.setManufacturer(manufacturer);
        airplane.setMaxCoach(maxCoach);
        airplane.setMaxFirst(maxFirst);

        return airplane;
    }

    /**
     * Builds a DOM tree from an XML string
     *
     * Parses the XML file and returns a DOM tree that can be processed
     *
     * @param xmlString XML String containing set of objects
     * @return DOM tree from parsed XML or null if exception is caught
     */
    static private Document buildDomDoc (String xmlString) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xmlString));

            return docBuilder.parse(inputSource);
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieve character data from an element if it exists
     *
     * @param e is the DOM Element to retrieve character data from
     * @return the character data as String [possibly empty String]
     */
    private static String getCharacterDataFromElement (Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }
}
