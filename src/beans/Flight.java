package beans;

import util.HttpUtil;
import util.TimezoneMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by: Tomas on 2017/10/03.
 *
 * airplane: The model name of the plane, and max seats of each type of seat
 * flightTime: The flight time in minutes
 * dep/arrAirportCode: The unique code for departure and arrival airports
 * dep/arrTime: The time string of departure and arrival, "YYYY MMM DD HH:MM"
 * first/coachClassPrice: The price of 2 types of seats
 * first/coachClassBooked: The booked seats of 2 types of seats
 */
public class Flight {

    private Airplane airplane;
    private int flightTime;
    private String flightNumber;
    private String depAirportCode;
    private String depTime;
    private String localDepTime;
    private String localArrTime;
    private String arrAirportCode;
    private String arrTime;
    private String firstClassPrice;
    private String coachClassPrice;
    private int firstClassBooked;
    private int coachClassBooked;

    public Flight() {
        airplane = new Airplane();
        flightTime = 0;
        flightNumber = "";
        depAirportCode = "";
        arrAirportCode = "";
        depTime = "";
        arrTime = "";
        firstClassPrice = "";
        coachClassPrice = "";
        firstClassBooked = 0;
        coachClassBooked = 0;
    }

    public boolean isValid() {
        return flightTime >= 0 && !firstClassPrice.equals("") && !coachClassPrice.equals("") && !flightNumber.equals("");
    }

    public Flight(String planeName, int flightTime, String flightNumber, String depAirportCode
            , String depTime, String arrAirportCode, String arrTime, String firstClassPrice, String coachClassPrice
            , int firstClassBooked, int coachClassBooked) {
        airplane = new Airplane(planeName);
        this.flightTime = flightTime;
        this.flightNumber = flightNumber;
        this.depAirportCode = depAirportCode;
        this.depTime = depTime;
        this.arrAirportCode = arrAirportCode;
        this.arrTime = arrTime;
        this.firstClassPrice = firstClassPrice;
        this.coachClassPrice = coachClassPrice;
        this.firstClassBooked = firstClassBooked;
        this.coachClassBooked = coachClassBooked;

    }

    public String toString() {
        return flightNumber + " " + airplane.getName() + " " + depAirportCode + " " + depTime + " " + arrAirportCode
                + " " + arrTime + " " + firstClassBooked + " " + firstClassPrice + " " + coachClassBooked
                + " " + coachClassPrice;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public int getFlightTime() {
        return flightTime;
    }

    public String getLocalDepTime() {
        return localDepTime;
    }

    public String getLocalArrTime() {
        return localArrTime;
    }

    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
    }

    public String getDepAirportCode() {
        return depAirportCode;
    }

    public void setDepAirportCode(String depAirportCode) {
        this.depAirportCode = depAirportCode;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrAirportCode() {
        return arrAirportCode;
    }

    public void setArrAirportCode(String arrAirportCode) {
        this.arrAirportCode = arrAirportCode;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getFirstClassPrice() {
        return firstClassPrice;
    }

    public void setFirstClassPrice(String firstClassPrice) {
        this.firstClassPrice = firstClassPrice;
    }

    public String getCoachClassPrice() {
        return coachClassPrice;
    }

    public void setCoachClassPrice(String coachClassPrice) {
        this.coachClassPrice = coachClassPrice;
    }

    public int getFirstClassBooked() {
        return firstClassBooked;
    }

    public void setFirstClassBooked(int firstClassBooked) {
        this.firstClassBooked = firstClassBooked;
    }

    public int getCoachClassBooked() {
        return coachClassBooked;
    }

    public void setCoachClassBooked(int coachClassBooked) {
        this.coachClassBooked = coachClassBooked;
    }

    public String depTimeToDate() throws ParseException {
        SimpleDateFormat time = new SimpleDateFormat("yyyy MMM dd HH:mm z", Locale.ENGLISH);
        SimpleDateFormat date = new SimpleDateFormat("yyyy_MM_dd");
        date.setTimeZone(TimeZone.getTimeZone("GMT"));
        return date.format(time.parse(this.getDepTime()));
    }

    public String depTimeToNextDate() throws ParseException {
        SimpleDateFormat time = new SimpleDateFormat("yyyy MMM dd HH:mm z", Locale.ENGLISH);
        SimpleDateFormat date = new SimpleDateFormat("yyyy_MM_dd");
        date.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date temp = new Date();
        temp.setTime(time.parse(this.getDepTime()).getTime() + 24 * 60 * 60 * 1000);
        return date.format(temp);
    }

    public void setLocalTime() throws ParseException {
        SimpleDateFormat time = new SimpleDateFormat("yyyy MMM dd HH:mm z", Locale.ENGLISH);
        Date temp = new Date();
        /**
         * set departing airport local time
         */
        temp.setTime(time.parse(this.getDepTime()).getTime());
        time.setTimeZone(findTz(this.depAirportCode));
        this.localDepTime = time.format(temp);

        temp.setTime(time.parse(this.getArrTime()).getTime());
        time.setTimeZone(findTz(this.arrAirportCode));
        this.localArrTime = time.format(temp);
    }

    public boolean hasCoach() {
        int maxCoach = this.getAirplane().getMaxCoach();
        int curCoach = this.getCoachClassBooked();
        return (maxCoach - curCoach) > 0;
    }

    public boolean hasFirst() {
        int maxFirst = getAirplane().getMaxFirst();
        int curFirst = getFirstClassBooked();
        return (maxFirst - curFirst) > 0;
    }

    private TimeZone findTz(String code) {
        TimeZone tz = TimeZone.getDefault();
        for (Airport airport : HttpUtil.airports) {
            if (code.equals(airport.getCode())) {
                tz = TimeZone.getTimeZone(TimezoneMapper.latLngToTimezoneString(airport.getLatitude(), airport.getLongitude()));
                break;
            }
        }
        return tz;
    }
}
