package util;

import java.util.ArrayList;

import static util.Saps.TEAM_NAME;

/**
 * Created by: Tomas on 2017/11/01.
 */
class QueryFactory {
    /**
     * Return a query string that can be passed to HTTP URL to request list of airports
     *
     * @return the query String which can be appended to URL to form HTTP GET request
     */
    static String getAirports() {
        return "?team=" + TEAM_NAME + "&action=list&list_type=airports";
    }

    /**
     * Lock the server database so updates can be written
     *
     * @return the String written to HTTP POST to lock server database
     */
    static String lock() {
        return "team=" + TEAM_NAME + "&action=lockDB";
    }

    /**
     * Unlock the server database after updates are written
     *
     * @return the String written to the HTTP POST to unlock server database
     */
    static String unlock() {
        return "team=" + TEAM_NAME + "&action=unlockDB";
    }

    /**
     * Return a query string of departure flights from an airport at a date
     *
     * @param date the departure date
     * @param code the code of the airport
     * @return the query string of get departure flights from an airport at a date
     */
    static String getDepFlights(String date, String code) {
        return "?team=" + TEAM_NAME + "&action=list&list_type=departing&airport="+code+"&day="+date;
    }

    /**
     * Return a query string of arriving flights at an airport at a date
     * @param date the arrival date
     * @param code the code of airport
     * @return query string of get arrival flights at airport at a date
     */
    static String getArrFlights(String date, String code){
        return "?team=" + TEAM_NAME + "&action=list&list_type=departing&airport="+ code + "&day=" +date;
    }
    /**
     *
     * Return query string of getting all of the airplanes
     * @return the query string
     */
    static String getAirplanes() {
        return "?team=" + TEAM_NAME + "&action=list&list_type=airplanes";
    }

    static String order(ArrayList<String> flightNumbers, ArrayList<String> seatTypes) {
        StringBuilder result = new StringBuilder();
        result.append("<Flights>");
        int i=0;
        for(String flightNumber:flightNumbers){
            result.append("<Flight number="+flightNumber+" seating="+seatTypes.get(i)+"/>");
            i++;
        }
        result.append("</Flights>");
        return result.toString();
    }
}
