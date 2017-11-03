package DAO;

import beans.Flights;
import entity.Airplane;
import entity.Flight;
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
public class DaoFlight {
    public static Flights addAll (String xmlFlights) throws NullPointerException {
        Flights flights = new Flights();

        // Load the XML string into a DOM tree for ease of processing
        // then iterate over all nodes adding each airport to our collection
        Document docFlights = buildDomDoc (xmlFlights);
        assert docFlights != null;
        NodeList nodesFlights = docFlights.getElementsByTagName("Flight");

        for (int i = 0; i < nodesFlights.getLength(); i++) {
            Element elementFlight = (Element) nodesFlights.item(i);
            Flight flight = buildFlight (elementFlight);

            if (flight.isValid()) {
                flights.add(flight);
            }
        }

        return flights;
    }

    static private Flight buildFlight(Node nodeFlight) {
        Flight flight = new Flight();

         Airplane airplane;
         int flightTime;
         String flightNumber;
         String depAirportCode;
         String depTime;
         String arrAirportCode;
         String arrTime;
         double firstClassPrice;
         double coachClassPrice;
         int firstClassBooked;
         int coachClassBooked;

        // The flight element has attributes of Name and 3 character flight code
        Element elementAirport = (Element) nodeFlight;
        name = elementAirport.getAttributeNode("Name").getValue();
        code = elementAirport.getAttributeNode("Code").getValue();

        // The latitude and longitude are child elements
        Element elementLatLng;
        elementLatLng = (Element)elementAirport.getElementsByTagName("Latitude").item(0);
        latitude = Double.parseDouble(getCharacterDataFromElement(elementLatLng));

        elementLatLng = (Element)elementAirport.getElementsByTagName("Longitude").item(0);
        longitude = Double.parseDouble(getCharacterDataFromElement(elementLatLng));

        flight.setName(name);
        flight.setCode(code);
        flight.setLatitude(latitude);
        flight.setLongitude(longitude);

        return flight;
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
