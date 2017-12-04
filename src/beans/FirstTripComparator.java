package beans;
import beans.Trip;

import java.util.Comparator;
// @author ghh
public class FirstTripComparator implements Comparator<Trip> {

	
	 

	@Override
	public int compare(Trip t1, Trip t2) {
		// TODO Auto-generated method stub
		Double price1 = t1.getTotalPrice("FirstClass");
		Double price2 = t2.getTotalPrice("FirstClass");
		return price1.compareTo(price2);
	}


}
