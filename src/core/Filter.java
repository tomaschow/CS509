package core;

import beans.Trip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by: ghh on 2017/12/04.
 * Its functionalities are implemented in front end
 */
public class Filter {

    public static ArrayList<Trip> StopFilter(ArrayList<Trip> trips, int stop) {
        ArrayList<Trip> filterTrip = new ArrayList<Trip>();
        for (Trip trip : trips) {
            if (trip.getFlights().size() == (stop + 1)) {
                filterTrip.add(trip);

            }
        }
        return filterTrip;
    }

    public static int depParseTime(Trip trip) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy MMM dd HH:mm z");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.setTime(formatter.parse(trip.getFlight(0).getDepTime()));
        return calendar.get(Calendar.HOUR_OF_DAY);


    }

    public static int arrParseTime(Trip trip) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy MMM dd HH:mm z");
        int flightnum = trip.getFlights().size();
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.setTime(formatter.parse(trip.getFlight(flightnum - 1).getArrTime()));
        return calendar.get(Calendar.HOUR_OF_DAY);


    }
//	public static long getDepTime(Trip trip) throws ParseException{
//		SimpleDateFormat time = new SimpleDateFormat("yyyy MMM dd HH:mm z");
//
//		return time.parse(trip.getFlight(0).getDepTime()).getTime();
//	}
//
//	public static long getArrTime(Trip trip)throws ParseException {
//		SimpleDateFormat time = new SimpleDateFormat("yyyy MMM dd HH:mm z");
//		int flightnum = trip.getFlights().size();
//		return time.parse(trip.getFlight(flightnum-1).getArrTime()).getTime();
//	}

    public static String check_TimeArea(int hour) {
        if (hour < 11) {
            return "Morning";
        } else {
            if (11 <= hour & hour < 18) {
                return "Afternoon";
            }

            return "Evening";
        }
    }

    public static ArrayList<Trip> depTimeFilter(ArrayList<Trip> trips, String deptime) throws ParseException {

        ArrayList<Trip> filterTrip = new ArrayList<Trip>();

        for (Trip trip : trips) {
            if (check_TimeArea(depParseTime(trip)) == deptime) {
                filterTrip.add(trip);
            }
        }
        return filterTrip;

    }

    public static ArrayList<Trip> arrTimeFilter(ArrayList<Trip> trips, String arrtime) throws ParseException {

        ArrayList<Trip> filterTrip = new ArrayList<Trip>();

        for (Trip trip : trips) {

            if (check_TimeArea(arrParseTime(trip)) == arrtime) {
                filterTrip.add(trip);

            }
        }
        return filterTrip;

    }
}
