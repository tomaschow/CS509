package entity;

/**
 * Created by: Tomas on 2017/10/03.
 * Check toString() method! This method suits requirements of the demo
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
    private String arrAirportCode;
    private String arrTime;
    private double firstClassPrice;
    private double coachClassPrice;
    private int firstClassBooked;
    private int coachClassBooked;

    public Flight(){
        airplane = new Airplane();
        flightTime = 0;
        flightNumber = "";
        depAirportCode = "";
        arrAirportCode = "";
        depTime = "";
        arrTime = "";
        firstClassPrice = 0;
        coachClassPrice = 0;
        firstClassBooked = 0;
        coachClassBooked = 0;
    }
    boolean isValid(){
        if (flightTime < 0||firstClassPrice<0||coachClassPrice<0||flightNumber.equals(""))
        {
            return false;
        }
        return true;
    }
    public Flight(String planeName, int flightTime, String flightNumber, String depAirportCode
            , String depTime, String arrAirportCode, String arrTime, double firstClassPrice, double coachClassPrice
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
    @Override
    public String toString(){
        return flightNumber+" "+depAirportCode+" "+depTime+" "+arrAirportCode
                +" "+arrTime+" "+firstClassBooked+" "+firstClassPrice+" "+coachClassBooked
                +" "+coachClassPrice;
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

    public double getFirstClassPrice() {
        return firstClassPrice;
    }

    public void setFirstClassPrice(double firstClassPrice) {
        this.firstClassPrice = firstClassPrice;
    }

    public double getCoachClassPrice() {
        return coachClassPrice;
    }

    public void setCoachClassPrice(double coachClassPrice) {
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
}
