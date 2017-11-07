package util;

/**
 * Created by: Tomas on 2017/11/01.
 */
class QueryFactory {
    /**
     * Return a query string that can be passed to HTTP URL to request list of airports
     *
     * @param teamName is the name of the team to specify the data copy on server
     * @return the query String which can be appended to URL to form HTTP GET request
     */
    static String getAirports(String teamName) {
        return "?team=" + teamName + "&action=list&list_type=airports";
    }

    /**
     * Lock the server database so updates can be written
     *
     * @param teamName is the name of the team to acquire the lock
     * @return the String written to HTTP POST to lock server database
     */
    static String lock(String teamName) {
        return "team=" + teamName + "&action=lockDB";
    }

    /**
     * Unlock the server database after updates are written
     *
     * @param teamName is the name of the team holding the lock
     * @return the String written to the HTTP POST to unlock server database
     */
    static String unlock(String teamName) {
        return "team=" + teamName + "&action=unlockDB";
    }

    /**
     * Return a query string of departure flights from an airport at a date
     *
     * @param teamName the team name
     * @param date the departure time
     * @param code the code of the airport
     * @return the query string of get departure flights from an airport at a date
     */
    static String getDepFlights(String teamName, String date, String code) {
        return "?team=" + teamName + "&action=list&list_type=departing&airport="+code+"&day="+date;
    }

    static String getAirplanes(String teamName) {
        return "?team=" + teamName + "&action=list&list_type=airplanes";
    }
}
