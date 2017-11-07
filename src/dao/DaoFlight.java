package dao;

import beans.Flights;
import beans.Airplane;
import beans.Flight;
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
         String firstClassPrice;
         String coachClassPrice;
         int firstClassBooked;
         int coachClassBooked;

        // The base element of Flight
        Element elementFlight = (Element) nodeFlight;
        airplane = new Airplane(elementFlight.getAttributeNode("Airplane").getValue());
        flightTime = Integer.parseInt(elementFlight.getAttributeNode("FlightTime").getValue());
        flightNumber = elementFlight.getAttributeNode("Number").getValue();
        // The element of departure and arrival airports
        Element airportTemp = (Element) elementFlight.getElementsByTagName("Departure").item(0);
        Element code = (Element) airportTemp.getElementsByTagName("Code").item(0);
        Element time = (Element) airportTemp.getElementsByTagName("Time").item(0);
        depAirportCode = getCharacterDataFromElement(code);
        depTime = getCharacterDataFromElement(time);
        airportTemp = (Element) elementFlight.getElementsByTagName("Arrival").item(0);
        code = (Element) airportTemp.getElementsByTagName("Code").item(0);
        time = (Element) airportTemp.getElementsByTagName("Time").item(0);
        arrAirportCode = getCharacterDataFromElement(code);
        arrTime = getCharacterDataFromElement(time);
        // The element of seating
        Element seating = (Element) elementFlight.getElementsByTagName("Seating").item(0);
        Element seatTemp = (Element) seating.getElementsByTagName("FirstClass").item(0);
        firstClassPrice = seatTemp.getAttributeNode("Price").getValue();
        firstClassBooked = Integer.parseInt(getCharacterDataFromElement(seatTemp));
        seatTemp = (Element) seating.getElementsByTagName("Coach").item(0);
        coachClassPrice = seatTemp.getAttributeNode("Price").getValue();
        coachClassBooked = Integer.parseInt(getCharacterDataFromElement(seatTemp));
        // Initializing the object
        flight.setAirplane(airplane);
        flight.setFlightTime(flightTime);
        flight.setFlightNumber(flightNumber);
        flight.setDepAirportCode(depAirportCode);
        flight.setDepTime(depTime);
        flight.setArrAirportCode(arrAirportCode);
        flight.setArrTime(arrTime);
        flight.setFirstClassBooked(firstClassBooked);
        flight.setFirstClassPrice(firstClassPrice);
        flight.setCoachClassBooked(coachClassBooked);
        flight.setCoachClassPrice(coachClassPrice);
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
