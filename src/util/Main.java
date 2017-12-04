package util;

import beans.Airport;
import beans.Flight;
import beans.Trip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by: Tomas on 2017/09/24.
 * The entry point of tests
 * It is ready for demo on 11/07
 */
public class Main {
    public static void main(String[] args) throws ParseException {
        ArrayList<Trip> trips = MockResult.INSTANCE.init();
        for(Trip trip : trips){
            for(Flight flight:trip.getFlights()){
                System.out.println(flight.toString());
            }
        }
        System.out.println();
//        HttpUtil.INSTANCE.unlock();
//        ArrayList<String> flights = new ArrayList<>();
//        ArrayList<String> seats = new ArrayList<>();
//        flights.add("4972");
//        flights.add("4973");
//        seats.add("FirstClass");
//        seats.add("Coach");
//        HttpUtil.INSTANCE.order(flights, seats);
//        HttpUtil.INSTANCE.lock();
//        if(HttpUtil.INSTANCE.lock()){
//            System.out.println("lock success");
//            ArrayList<Airport> airports = HttpUtil.INSTANCE.getAirports();
//            if(airports.size()>0){
//                System.out.println("Warning, get data from a locked db");
//                for(Airport airport:airports){
//                    System.out.println(airport.getName());
//                }
//            }else{
//                System.out.println("locking mechanism normal");
//            }
//            if (HttpUtil.INSTANCE.unlock()) {
//                System.out.println("unlock success");
//            }else{
//                System.out.println("unlock failed");
//            }
//        }else{
//            System.out.println("lock failed");
//        }
//        ArrayList<String> flights = new ArrayList<>();
//        ArrayList<String> seats = new ArrayList<>();
//        flights.add("4972");
//        flights.add("4973");
//        seats.add("FirstClass");
//        seats.add("Coach");
//        System.out.println(QueryFactory.order(flights,seats));
//        //ArrayList<Airplane> airplanes = HttpUtil.INSTANCE.getAirplanes(); // Not used for now
//        ArrayList<Airport> airports = HttpUtil.INSTANCE.getAirports(); // Used for validating airport codes
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Input date (yyyy_MM_dd) please!");
//        String date = scanner.nextLine();
//        while (!isValidDate(date)) {
//            System.out.println("Input date (yyyy_MM_dd) please!");
//            date = scanner.nextLine();
//        }
//        System.out.println("Input the code of departure airport please!");
//        String code = scanner.nextLine();
//        while(!isValidCode(airports,code)){
//            System.out.println("The code is not valid, please make sure it's an existing 3-uppercase-code.");
//            System.out.println("Input the code of departure airport please!");
//            code = scanner.nextLine();
//        }
//        // Only date with format of "yyyy_MM_dd" can be accepted.
//        // Only airport code with 3 uppercase letters can be accepted.
//        displayFlights(HttpUtil.INSTANCE.getFlights(true, date, code));
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
}
