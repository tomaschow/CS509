package beans;
//new import

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

	public boolean isValid() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd hh:mm z", Locale.ENGLISH);
		if (this.flights.size() == 0 || this.flights.size() > 3) {
			return false;
		}
		if (this.flights.size() == 1) {
			return true;
		}
		for (int i = 0; i < (this.flights.size() - 1); i++) {
			try {
				Date before = format.parse(flights.get(i).getArrTime());
				Date afterOld = format.parse(flights.get(i + 1).getDepTime());
				Date afterNew = new Date(afterOld.getTime() - (STOPOVER_IN_MINUTE * ONE_MINUTE_IN_MILLIS));
				// If the departure date is earlier than previous departure..
				if (before.after(afterNew)) {
					System.out.println("Arr Time of " + i + " is " + flights.get(i).getArrTime() +
							", while Dep Time of " + (i + 1) + " is " + flights.get(i + 1).getDepTime() + "; Stopover time is" +
							STOPOVER_IN_MINUTE + " minutes");
					return false;
				}
			} catch (ParseException e) {
				System.out.println("The date is not valid");
				return false;
			}
		}
		return true;
	}


	public double getTotalPrice(){
		double totalPrice = 0.0;
		for (Flight flight : this.flights) {
			if(flight.hasCoach()){
				// sum up coach price as default
				//这里做了个假设：如果没有coach那么一定有first class
				String coachPrice = flight.getCoachClassPrice();
				coachPrice = coachPrice.substring(1, coachPrice.length()).replaceAll(",","");
				totalPrice += Double.parseDouble(coachPrice);
			}else{
				String firstPrice = flight.getFirstClassPrice();
				firstPrice = firstPrice.substring(1, firstPrice.length()).replaceAll(",","");
				totalPrice += Double.parseDouble(firstPrice);
			}
		}
		return round(totalPrice, 2);
	}

//    //@author ghh
//    @Deprecated
//    public double getTotalPrice(String seatPreference) {
//        double totalPrice = 0.0;
//        boolean firstClass = false;
//        if (this.flights == null || this.flights.size() == 0) {
//            return 0.00;
//        }
//        if (seatPreference.equals("FirstClass")) {
//            firstClass = true;
//        } else {
//            firstClass = false;
//        }
//        for (Flight flight : this.flights) {
//            if (firstClass) {
//                String price = flight.getFirstClassPrice();
//                price = price.substring(1, price.length());
//                totalPrice += Double.parseDouble(price);
//            } else {
//                String price = flight.getCoachClassPrice();
//                price = price.substring(1, price.length());
//                totalPrice += Double.parseDouble(price);
//            }
//        }
//        return round(totalPrice, 2);
//    }

	/**
	 * get total number of Flights in the reservation option
	 *
	 * @return the number of flights
	 */
	public int getNumFlights() {
		try {
			return this.flights.size();
		} catch (Exception ex) {
			return 0;
		}
	}

	public Flight getFlight(int index) {
		Flight flight;
		try {
			flight = this.flights.get(index);
		} catch (Exception ex) {
			flight = null;
		}
		return flight;
	}
	public String getDepDateTime(){
		return getFlight(0).getDepTime();
	}
	public String getArrDateTime(){
		return getFlight(getNumFlights()).getArrTime();
	}
	public String getTotalTime() {

		DateTimeFormatter flightDateFormat = DateTimeFormatter.ofPattern("yyyy MMM d HH:mm z");
		long totalTime = 0;
		if (this.flights == null || this.flights.size() == 0) {
			return "00:00";
		}
		try {
			LocalDateTime departTimeLocal = LocalDateTime.parse(this.getFlight(0).getDepTime(), flightDateFormat);
			ZonedDateTime departTimeZoned = departTimeLocal.atZone(ZoneId.of("GMT"));
			long departTime = departTimeZoned.toInstant().toEpochMilli();
			LocalDateTime arrivalTimeLocal = LocalDateTime.parse(this.getFlight(getNumFlights() - 1).getArrTime(), flightDateFormat);
			ZonedDateTime arrivalTimeZoned = arrivalTimeLocal.atZone(ZoneId.of("GMT"));
			long arrivalTime = arrivalTimeZoned.toInstant().toEpochMilli();
			totalTime = arrivalTime - departTime;
		} catch (DateTimeParseException ex) {
			return "INVALID";
		}
		return String.format("%02d:%02d",
				TimeUnit.MILLISECONDS.toHours(totalTime),
				TimeUnit.MILLISECONDS.toMinutes(totalTime) % TimeUnit.HOURS.toMinutes(1)
		);
	}

	private double round(double value, int places) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}