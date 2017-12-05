package core;

import beans.*;
import util.HttpUtil;
import util.Main;
import util.TimezoneMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
    private int cnt =0;

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

    public void setDepDate(String depDate) throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("yyyy_MM_dd");
        date.setTimeZone(findTz(this.depAirportCode));
        Date temp = new Date();
        temp.setTime(date.parse(depDate).getTime());
        date.setTimeZone(findTz("GMT"));
        this.depDate = date.format(temp);
    }

    public boolean isNonstop() {
        return isNonstop;
    }

    public void setNonstop(boolean nonstop) {
        isNonstop = nonstop;
    }

    public int getCnt() {
        return cnt;
    }

    public ArrayList<Trip> commenceSearch( ) throws ParseException {
        ArrayList<Flight> previousFlights = new ArrayList<Flight>();
        tripSearch(0,previousFlights,this.depAirportCode, this.depDate);
        return trips;
    }
    private void tripSearch(int index, ArrayList<Flight> previousFlights,String departureCode, String departureDate ) throws ParseException {

        if (index > 2){ return;}
        int cacheIndex = -1;
        SimpleDateFormat time = new SimpleDateFormat("yyyy MMM dd HH:mm z", Locale.ENGLISH);
        /**
         * if this list already exists
         */
        for(int i=0; i<caches.size();i++ ){
           if(caches.get(i).getCode().equals(departureCode) ){cacheIndex=i;break;}
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
        if(!departureCode.equals(this.depAirportCode))cache.addFlights(HttpUtil.INSTANCE.getFlights(true, this.depTimeToNextDate(), departureCode));
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
                //System.out.println(index);
                Long timeDiff = time.parse(caches.get(cacheIndex).getFlights().get(j).getDepTime()).getTime() - time.parse(previousFlights.get(previousFlights.size()-1).getArrTime()).getTime();
                if(timeDiff/(60*1000) < 30){
                    //System.out.println("continue1");
                    continue;
                }else if(timeDiff/(60*1000) > 240){
                    return;
                }
                //System.out.println("valid flight");
            }
            /**
             * Check if it loops back to the departure airport or no tickets left
             */
            int ticketsLeft = caches.get(cacheIndex).getFlights().get(j).getAirplane().getMaxFirst()
                    +caches.get(cacheIndex).getFlights().get(j).getAirplane().getMaxCoach()
                    - caches.get(cacheIndex).getFlights().get(j).getFirstClassBooked()
                    - caches.get(cacheIndex).getFlights().get(j).getCoachClassBooked();
            if(caches.get(cacheIndex).getFlights().get(j).getArrAirportCode().equals(this.depAirportCode) || ticketsLeft < 0){
                //System.out.println("continue2");
                continue;
            }
            /**
             * If this is not a direct flight to arr
             */
           if(!caches.get(cacheIndex).getFlights().get(j).getArrAirportCode().equals(this.arrAirportCode)){
               //System.out.println("recursion");
               ArrayList<Flight> parentFlights = new ArrayList<>();
               parentFlights.addAll(previousFlights);
               parentFlights.add(caches.get(cacheIndex).getFlights().get(j));
               int newIndex = index + 1;
               tripSearch(newIndex, parentFlights, caches.get(cacheIndex).getFlights().get(j).getArrAirportCode(), caches.get(cacheIndex).getFlights().get(j).depTimeToDate());
               //tripSearch(newIndex, parentFlights, caches.get(cacheIndex).getFlights().get(j).getArrAirportCode(), caches.get(cacheIndex).getFlights().get(j).depTimeToNextDate());
           }
            else{
               ArrayList<Flight> flights = new ArrayList<>();
               flights.addAll(previousFlights);
               flights.add(caches.get(cacheIndex).getFlights().get(j));
               //System.out.println(caches.get(cacheIndex).getFlights().get(j));
//               for(Flight flight : flights){
//                    flight.setLocalTime();
//               }
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
   public  String depTimeToNextDate() throws ParseException {
         SimpleDateFormat date = new SimpleDateFormat("yyyy_MM_dd");
        date.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date temp = new Date();
        temp.setTime(date.parse(this.getDepDate()).getTime()+24*60*60*1000);
        return date.format(temp);
    }

    private TimeZone findTz(String code){
        TimeZone tz = TimeZone.getDefault();
        for(Airport airport: HttpUtil.airports){
            if(code.equals(airport.getCode())){
                tz = TimeZone.getTimeZone(TimezoneMapper.latLngToTimezoneString(airport.getLatitude(), airport.getLongitude()));
                break;
            }
        }
        return tz;
    }
}
