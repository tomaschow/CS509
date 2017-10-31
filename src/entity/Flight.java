package entity;

/**
 * Created by: Tomas on 2017/10/03.
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
    private String depAirportCode;
    private String depTime;
    private String arrAirportCode;
    private String arrTime;
    private double firstClassPrice;
    private double coachClassPrice;
    private int firstClassBooked;
    private int coachClassBooked;

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
