package dao;

import beans.Airports;
import beans.Airport;
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
 * Created by: Tomas on 2017/11/01.
 */
public class DaoAirport {
    public static Airports addAll (String xmlAirports) throws NullPointerException {
        Airports airports = new Airports();

        // Load the XML string into a DOM tree for ease of processing
        // then iterate over all nodes adding each airport to our collection
        Document docAirports = buildDomDoc (xmlAirports);
        assert docAirports != null;
        NodeList nodesAirports = docAirports.getElementsByTagName("Airport");

        for (int i = 0; i < nodesAirports.getLength(); i++) {
            Element elementAirport = (Element) nodesAirports.item(i);
            Airport airport = buildAirport (elementAirport);

            if (airport.isValid()) {
                airports.add(airport);
            }
        }

        return airports;
    }

    static private Airport buildAirport (Node nodeAirport) {
        Airport airport = new Airport();

        String name;
        String code;
        double latitude;
        double longitude;

        // The airport element has attributes of Name and 3 character airport code
        Element elementAirport = (Element) nodeAirport;
        name = elementAirport.getAttributeNode("Name").getValue();
        code = elementAirport.getAttributeNode("Code").getValue();

        // The latitude and longitude are child elements
        Element elementLatLng;
        elementLatLng = (Element)elementAirport.getElementsByTagName("Latitude").item(0);
        latitude = Double.parseDouble(getCharacterDataFromElement(elementLatLng));

        elementLatLng = (Element)elementAirport.getElementsByTagName("Longitude").item(0);
        longitude = Double.parseDouble(getCharacterDataFromElement(elementLatLng));

        airport.setName(name);
        airport.setCode(code);
        airport.setLatitude(latitude);
        airport.setLongitude(longitude);

        return airport;
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
