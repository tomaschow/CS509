package beans;
import java.util.Comparator;
// @author ghh
public class TripPriceComparator implements Comparator<Trip> {

	@Override
	public int compare(Trip t1, Trip t2) {
		// TODO Auto-generated method stub
		Double price1 = t1.getTotalPrice();
		Double price2 = t2.getTotalPrice();
		return price1.compareTo(price2);
	}


}
