package core;

import beans.Trip;
import beans.TripArrTimeComparator;
import beans.TripFlightTimeComparator;
import beans.TripPriceComparator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author ghh
 * search result is sorted by price in descending order by default. The rest of the sorting is implemented in front end.
 */
public class Sort {


    public static void sortPrice(boolean ascending, ArrayList<Trip> tripOptions) {

        tripOptions.sort(new TripPriceComparator());

        if (!ascending) {
            Collections.reverse(tripOptions);
        }
    }

    /**
     * This method takes a list of ReservationOption objects and sorts them
     * based on price in ascending or descending order depending on input
     *
     * @param ascending (required) true if ascending, false if descending order
     */
    public static void sortDepTime(boolean ascending, ArrayList<Trip> tripOptions) {
        tripOptions.sort(new TripFlightTimeComparator());
        if (!ascending) {
            Collections.reverse(tripOptions);
        }
    }

    public static void sortArrTime(boolean ascending, ArrayList<Trip> tripOptions) {
        tripOptions.sort(new TripArrTimeComparator());
        if (!ascending) {
            Collections.reverse(tripOptions);
        }
    }

    public static void sortFlightTime(boolean ascending, ArrayList<Trip> tripOptions) {
        tripOptions.sort(new TripFlightTimeComparator());
        if (!ascending) {
            Collections.reverse(tripOptions);
        }
    }

}
