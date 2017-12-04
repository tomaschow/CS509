package beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static util.Saps.ONE_MINUTE_IN_MILLIS;
import static util.Saps.STOPOVER_IN_MINUTE;

/**
 * Created by: Tomas on 2017/11/09.
 */
public class Trip {
    private long tripID;
    private ArrayList<Flight> flights;

    public long getTripID() {
        return tripID;
    }

    public void setTripID(long tripID) {
        this.tripID = tripID;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public boolean isValid(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd hh:mm z", Locale.ENGLISH);
        if(this.flights.size() == 0 || this.flights.size() > 3){
            return false;
        }
        if(this.flights.size() == 1){
            return true;
        }
        for (int i=0;i<(this.flights.size()-1);i++) {
            try {
                Date before = format.parse(flights.get(i).getArrTime());
                Date afterOld = format.parse(flights.get(i+1).getDepTime());
                Date afterNew = new Date(afterOld.getTime()-(STOPOVER_IN_MINUTE * ONE_MINUTE_IN_MILLIS));
                // If the departure date is earlier than previous departure..
                if(before.after(afterNew)){
                    System.out.println("Arr Time of "+i+" is "+flights.get(i).getArrTime()+
                    ", while Dep Time of "+(i+1)+" is "+flights.get(i+1).getDepTime()+"; Stopover time is"+
                    STOPOVER_IN_MINUTE+" minutes");
                    return false;
                }
            } catch (ParseException e) {
                System.out.println("The date is not valid");
                return false;
            }
        }
        return true;
    }

}
