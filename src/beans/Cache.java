package beans;

import java.util.ArrayList;

/**
 * Created by: Tomas on 2017/11/09.
 * The cached list of flights
 */
public class Cache {
    private String code; // Airport code
    private String date; // Date of the list
    private boolean isDeparture; // true=departure, false=arrival
    private ArrayList<Flight> flights;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDeparture() {
        return isDeparture;
    }

    public void setDeparture(boolean departure) {
        isDeparture = departure;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }
}
