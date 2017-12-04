package util;

import beans.Airport;
import beans.Flight;
import beans.Trip;
import core.Search;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by: Tomas on 2017/09/24.
 * The entry point of tests
 * It is ready for demo on 11/07
 */
public class Main {
    public static void main(String[] args) throws ParseException {
        Search search = new Search();
        search.setAirplanes(HttpUtil.INSTANCE.getAirplanes());
        search.setAirports(HttpUtil.INSTANCE.getAirports());
        search.setArrAirportCode("ATL");
        search.setDepAirportCode("BOS");
        search.setDepDate("2017_12_10");
        //search.commenceSearch();
        displayTrips(search.commenceSearch());
        System.out.println(search.getCnt());
    }

    private static boolean isValidCode(ArrayList<Airport> airports, String codeString) {
        if(codeString.matches("[A-Z]{3}")) {
            for (Airport airport : airports) {
                if (codeString.equals(airport.getCode()))
                    return true;
            }
        }
        return false;
    }

    private static boolean isValidDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd hh:mm z", Locale.ENGLISH);
        try {
            format.parse(dateString);
        } catch (ParseException e) {
            System.out.println("The date is not valid, please input again.");
            return false;
        }
        return true;
    }

    /**
     * Displays the flights in console, for demo purposes
     * @param flights A collection of Flight, coming from HttpUtil.getFlights()
     */
    private static void displayFlights(ArrayList<Flight> flights) {
        System.out.println("\nThe List of Flights\nFlightNumber\tDeparture\tDepartureTime\t\t\t\tArrival\tArrivalTime\t\t\t\t\t" +
                "FC-Booked\tFC-Price\t\t\tEC-Booked\tEC-Price\t\t" +
                "TravelTime(minutes)");
        for (Flight flight : flights) {
            System.out.println(flight.getFlightNumber()+"\t\t\t"+flight.getDepAirportCode()
                    +"\t\t\t"+flight.getDepTime()+"\t\t"+flight.getArrAirportCode()
                    +"\t\t"+flight.getArrTime()+"\t\t"+flight.getFirstClassBooked()
                    +"\t\t\t"+flight.getFirstClassPrice()+"\t\t\t\t"+flight.getCoachClassBooked()
                    +"\t\t\t"+flight.getCoachClassPrice()+"\t\t\t"+flight.getFlightTime());
        }
    }
    private static void displayTrips(ArrayList<Trip> trips) {
        System.out.println("\nThe List of Trips\nTripID\t");
        for (Trip trip : trips) {
            System.out.println(trip.getTripID()+"\t\t\t");
            displayFlights(trip.getFlights());
        }
    }
}
