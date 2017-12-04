package beans;
import java.util.Comparator;
import beans.Trip;
//ghh
public class CoachTripComparator implements Comparator<Trip> {

   @Override
	public int compare(Trip t1, Trip t2) {
		// TODO Auto-generated method stub
		Double price1 = t1.getTotalPrice("Coach");
		Double price2 = t2.getTotalPrice("Coach");
		return price1.compareTo(price2);
	}
}
