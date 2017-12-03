package core;

import beans.Airplane;
import beans.Airport;
import beans.Trip;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by: Tomas on 2017/11/09.
 */
public class Search {
    private String depAirportCode;
    private String arrAirportCode;
    private SimpleDateFormat depDate;
    private boolean isNonstop;
    private ArrayList<Airport> airports;
    private ArrayList<Airplane> airplanes;

    public ArrayList<Airport> getAirports() {
        return airports;
    }

    public void setAirports(ArrayList<Airport> airports) {
        this.airports = airports;
    }

    public ArrayList<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(ArrayList<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    public String getDepAirportCode() {
        return depAirportCode;
    }

    public void setDepAirportCode(String depAirportCode) {
        this.depAirportCode = depAirportCode;
    }

    public String getArrAirportCode() {
        return arrAirportCode;
    }

    public void setArrAirportCode(String arrAirportCode) {
        this.arrAirportCode = arrAirportCode;
    }

    public SimpleDateFormat getDepDate() {
        return depDate;
    }

    public void setDepDate(SimpleDateFormat depDate) {
        this.depDate = depDate;
    }

    public boolean isNonstop() {
        return isNonstop;
    }

    public void setNonstop(boolean nonstop) {
        isNonstop = nonstop;
    }

    public ArrayList<Trip> commenceSearch(){
        /**
         * 根据参数搜索，返回结果集Arraylist<Trip>
         */
    }
    private boolean isValid(){
        /**
         * 验证两个机场是否存在于airports
         *
          */

    }
}
