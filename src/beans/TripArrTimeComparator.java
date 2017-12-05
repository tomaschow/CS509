package beans;
import java.util.Comparator;
public class TripArrTimeComparator implements Comparator<Trip> {

    @Override
    public int compare(Trip t1, Trip t2) {
        // TODO Auto-generated method stub
        String time1 = t1.getArrDateTime();
        String time2 = t2.getArrDateTime();
        return time1.compareTo(time2);
    }
}