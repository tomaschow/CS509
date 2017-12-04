package core;
import java.util.ArrayList;
import java.util.Collections;

import beans.*;

/**
  @author ghh
 */
public class Sort {

	 
	public void sortPrice(boolean ascending,ArrayList<Trip> tripOptions,String seatType) {

		if(seatType.equals("FirstClass")) {
			Collections.sort(tripOptions, new FirstTripComparator());
		} else if(seatType.equals("Coach")) {
			Collections.sort(tripOptions, new CoachTripComparator());
		}
		if (!ascending) {
			Collections.reverse(tripOptions);
		}
	}
	/**
	 * This method takes a list of ReservationOption objects and sorts them
	 * based on price in ascending or descending order depending on input
	 * @param ascending (required) true if ascending, false if descending order
	 * @param resOptions list(required) the reservation options to sort
	 */
	public void sortTime(boolean ascending, ArrayList<Trip> tripOptions) {
		Collections.sort(tripOptions, new TimeSortTripCompator());
		if (!ascending) {
			Collections.reverse(tripOptions);
		}
	}
}
