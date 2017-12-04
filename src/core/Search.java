package core;

import beans.*;
import util.HttpUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by: Tomas on 2017/11/09.
 */
public class Search {
    private String depAirportCode;
    private String arrAirportCode;
    private String depDate;
    private boolean isNonstop;
    private ArrayList<Airport> airports;
    private ArrayList<Airplane> airplanes;
    private ArrayList<Cache> caches = new ArrayList<Cache>();
    private ArrayList<Trip> trips = new ArrayList<Trip>();

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

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public boolean isNonstop() {
        return isNonstop;
    }

    public void setNonstop(boolean nonstop) {
        isNonstop = nonstop;
    }

    public ArrayList<Trip> commenceSearch( ) throws ParseException {
        System.out.println("=========In search=========/n");
        ArrayList<Flight> previousFlights = new ArrayList<Flight>();
        tripSearch(0,previousFlights,this.depAirportCode, this.depDate);
        return trips;
    }
    public void tripSearch(int index, ArrayList<Flight> previousFlights,String departureCode, String departureDate ) throws ParseException {

        if (index > 2){ return;}
        int cacheIndex = -1;
        SimpleDateFormat time = new SimpleDateFormat("yyyy MMM dd HH:mm z");
        /**
         * if this list already exists
         */
        for(int i=0; i<caches.size();i++ ){
           if(caches.get(i).getCode().equals(departureCode) && caches.get(i).getDate().equals(departureDate)){cacheIndex=i;}
        }
        /**
         * get new cache
         */
        if(cacheIndex<0) {
        Cache cache = new Cache();
        cache.setCode(departureCode);
        cache.setDate(departureDate);
        cache.setDeparture(true);
        cache.setFlights(HttpUtil.INSTANCE.getFlights(true, departureDate, departureCode));
//        for (Flight flight: cache.getFlights()){
//            System.out.println(flight);
//        }
        caches.add(cache);
        cacheIndex = caches.size()-1;
        }
        /**
         * iterate through the list of flights
         */
        for (int j = 0; j< caches.get(cacheIndex).getFlights().size();j++){
            /**
             * Check whether the time between each connection flight is valid
             */
            if(index > 0){
                Long timeDiff = time.parse(caches.get(cacheIndex).getFlights().get(j).getDepTime()).getTime() - time.parse(previousFlights.get(previousFlights.size()-1).getArrTime()).getTime();
                if(timeDiff/(60*1000) < 30){

                    continue;
                }else if(timeDiff/(60*1000) > 240){

                    return;
                }
            }
            /**
             * Check if it loops back to the departure airport
             */
            if(caches.get(cacheIndex).getFlights().get(j).getArrAirportCode().equals(this.depAirportCode)){
                continue;
            }
            /**
             * If there's no direct flight from dep to arr
             */
           if(!caches.get(cacheIndex).getFlights().get(j).getArrAirportCode().equals(this.arrAirportCode)){
               ArrayList<Flight> parentFlights = new ArrayList<>();
               parentFlights.addAll(previousFlights);
               parentFlights.add(caches.get(cacheIndex).getFlights().get(j));
               int newIndex = index + 1;
               tripSearch(newIndex, parentFlights, caches.get(cacheIndex).getFlights().get(j).getArrAirportCode(), caches.get(cacheIndex).getFlights().get(j).depTimeToDate());
               tripSearch(newIndex, parentFlights, caches.get(cacheIndex).getFlights().get(j).getArrAirportCode(), caches.get(cacheIndex).getFlights().get(j).depTimeToNextDate());
           }
            else{
               ArrayList<Flight> flights = new ArrayList<>();
               flights.addAll(previousFlights);
               flights.add(caches.get(cacheIndex).getFlights().get(j));
               //System.out.println(caches.get(cacheIndex).getFlights().get(j));
               Trip trip = new Trip();
               trip.setFlights(flights);
               trip.setTripID(trips.size());
               trips.add(trip);
           }
        }
    }
    private boolean isValid(){
        /**
         * 验证两个机场是否存在于airports
         *
          */
        return false;
    }
}
