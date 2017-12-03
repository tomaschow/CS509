package util;

import beans.Airplane;
import beans.Flight;
import beans.Trip;

import java.util.ArrayList;

/**
 * Created by: Tomas on 2017/12/03.
 */
public enum MockResult {
INSTANCE;
    public ArrayList<Trip> init(){
        ArrayList<Trip> mockTrips = new ArrayList<>();
        for(int i=0;i<5;i++){
            Trip trip = new Trip();
            trip.setTripID(i);
            ArrayList<Flight> flights = new ArrayList<>();
            Flight flight = new Flight();
            flight.setAirplane(new Airplane("Boeing","737",10,100));
            flight.setArrAirportCode("BOS");
            flight.setArrTime("2017 Dec 10 00:12 GMT");
            flight.setDepAirportCode("SFO");
            flight.setDepTime("2017 Dec 10 00:41 GMT");
            flight.setCoachClassBooked(79+i);
            flight.setFirstClassBooked(8);
            flight.setCoachClassPrice("$100");
            flight.setFirstClassPrice("$300");
            flight.setFlightNumber("498"+i);
            flight.setFlightTime(230);
            flights.add(flight);
            trip.setFlights(flights);
            mockTrips.add(trip);
        }
        return mockTrips;
    }
}
