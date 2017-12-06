package util;

import beans.Airplane;
import beans.Flight;
import beans.Trip;

import java.util.ArrayList;

/**
 * Created by: Tomas on 2017/12/03.
 * This is to mock a trip list to test the front end UI
 */
public enum MockResult {
    INSTANCE;

    public ArrayList<Trip> init() {
        ArrayList<Trip> mockTrips = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Trip trip = new Trip();
            trip.setTripID(i);
            ArrayList<Flight> flights = new ArrayList<>();
            Flight flight = new Flight();
            flight.setAirplane(new Airplane("Boeing", "737", 10, 100));
            flight.setArrAirportCode("BOS");
            flight.setArrTime("2017 Dec 10 00:12 GMT");
            if (i == 3 || i == 1)
                flight.setDepAirportCode("SFO");
            else
                flight.setDepAirportCode("JFK");
            if (i == 2 || i == 0)
                flight.setDepTime("2017 Dec 10 00:41 GMT");
            else
                flight.setDepTime("2017 Dec 11 23:12 GMT");
            flight.setCoachClassBooked(79 + i);
            flight.setFirstClassBooked(8);
            if (i == 2 || i == 4)
                flight.setCoachClassPrice("$100");
            else
                flight.setCoachClassPrice("$90");
            flight.setFirstClassPrice("$300");
            flight.setFlightNumber("498" + i);
            flight.setFlightTime(230 + i);

            flights.add(flight);
            Flight flight1 = new Flight();
            flight1.setAirplane(new Airplane("Boeing", "737", 10, 100));
            flight1.setArrAirportCode("BOS");
            flight1.setArrTime("2017 Dec 10 00:12 GMT");
            if (i == 3 || i == 1)
                flight1.setDepAirportCode("SFO");
            else
                flight1.setDepAirportCode("JFK");
            if (i == 2 || i == 0)
                flight1.setDepTime("2017 Dec 10 00:41 GMT");
            else
                flight1.setDepTime("2017 Dec 13 23:12 GMT");
            flight1.setCoachClassBooked(70 + i);
            flight1.setFirstClassBooked(4);
            flight1.setCoachClassPrice("$100");
            flight1.setFirstClassPrice("$300");
            flight1.setFlightTime(230 + i);
            flight1.setFlightNumber("632" + i);
            flights.add(flight1);
            trip.setFlights(flights);
            mockTrips.add(trip);
        }
        return mockTrips;
    }
}
