package beans;
import java.util.Comparator;
public class TripFlightTimeComparator implements Comparator<Trip> {
	
		@Override
		public int compare(Trip t1, Trip t2) {
			// TODO Auto-generated method stub
			String time1 = t1.getTotalTime();
			String time2 = t2.getTotalTime();
			return time1.compareTo(time2);
		}
}



