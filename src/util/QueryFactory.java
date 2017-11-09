package util;

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
     * @param date the departure time
     * @param code the code of the airport
     * @return the query string of get departure flights from an airport at a date
     */
    static String getDepFlights(String date, String code) {
        return "?team=" + TEAM_NAME + "&action=list&list_type=departing&airport="+code+"&day="+date;
    }

    /**
     *
     * Return query string of getting all of the airplanes
     * @return the query string
     */
    static String getAirplanes() {
        return "?team=" + TEAM_NAME + "&action=list&list_type=airplanes";
    }
}
