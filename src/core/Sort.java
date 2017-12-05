package core;
import beans.Trip;
import beans.TripArrTimeComparator;
import beans.TripFlightTimeComparator;
import beans.TripPriceComparator;

import java.util.ArrayList;
import java.util.Collections;

/**
  @author ghh
 */
public class Sort {

	 
	public void sortPrice(boolean ascending,ArrayList<Trip> tripOptions) {

		tripOptions.sort(new TripPriceComparator());

		if (!ascending) {
			Collections.reverse(tripOptions);
		}
	}
	/**
	 * This method takes a list of ReservationOption objects and sorts them
	 * based on price in ascending or descending order depending on input
	 * @param ascending (required) true if ascending, false if descending order
	 *
	 */
	public void sortDepTime(boolean ascending, ArrayList<Trip> tripOptions) {
		tripOptions.sort(new TripFlightTimeComparator());
		if (!ascending) {
			Collections.reverse(tripOptions);
		}
	}
	public void sortArrTime(boolean ascending, ArrayList<Trip> tripOptions) {
		tripOptions.sort(new TripArrTimeComparator());
		if (!ascending) {
			Collections.reverse(tripOptions);
		}
	}
	public void sortFlightTime(boolean ascending, ArrayList<Trip> tripOptions) {
		tripOptions.sort(new TripFlightTimeComparator());
		if (!ascending) {
			Collections.reverse(tripOptions);
		}
	}

}
