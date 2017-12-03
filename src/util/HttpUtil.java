package util;

import beans.Airplane;
import beans.Airport;
import beans.Flight;
import dao.DaoAirplane;
import dao.DaoAirport;
import dao.DaoFlight;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static util.Saps.TEAM_NAME;

/**
 * Created by: Tomas on 2017/10/03.
 */
public enum HttpUtil {
    INSTANCE;

    private static String urlBase = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";

    /**
     *
     * @param date the departure date
     * @param code the code of the airport
     * @return ArrayList<Flight> that contains departure flights
     */
    public ArrayList<Flight> getFlights (boolean isDeparture, String date,String code) {

        URL url;
        HttpURLConnection connection;
        BufferedReader reader;
        String line;
        StringBuilder result = new StringBuilder();
        String xmlFlights;
        ArrayList<Flight> flights;

        try {
            if(isDeparture) {
                url = new URL(urlBase + QueryFactory.getDepFlights(date, code));
            }else{
                url = new URL(urlBase + QueryFactory.getArrFlights(date, code));
            }
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", TEAM_NAME);
            int responseCode = connection.getResponseCode();
            if (responseCode >= HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        xmlFlights = result.toString();
        flights = DaoFlight.addAll(xmlFlights);
        return flights;
    }

    /**
     * Get the airport list from server
     * @return A list of airports
     */
    public ArrayList<Airport> getAirports () {

        URL url;
        HttpURLConnection connection;
        BufferedReader reader;
        String line;
        StringBuilder result = new StringBuilder();

        String xmlAirports;
        ArrayList<Airport> airports;

        try {
            url = new URL(urlBase + QueryFactory.getAirports());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", TEAM_NAME);
            int responseCode = connection.getResponseCode();
            if (responseCode >= HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        xmlAirports = result.toString();
        airports = DaoAirport.addAll(xmlAirports);
        return airports;

    }
    /**
     * Lock the database for updating by the specified team. The operation will fail if the lock is held by another team.
     *
     * @return true if the server was locked successfully, else false
     */
    public boolean lock () {
        URL url;
        HttpURLConnection connection;

        try {
            url = new URL(urlBase);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", TEAM_NAME);
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String params = QueryFactory.lock();

            connection.setDoOutput(true);
            DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(params);
            writer.flush();
            writer.close();

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' to lock database");
            System.out.println(("\nResponse Code : " + responseCode));

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            System.out.println(response.toString());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Unlock the database previous locked by specified team. The operation will succeed if the server lock is held by the specified
     * team or if the server is not currently locked. If the lock is held be another team, the operation will fail.
     *
     * The server interface to unlock the server interface uses HTTP POST protocol
     *
     * @return true if the server was successfully unlocked.
     */
    public boolean unlock () {
        URL url;
        HttpURLConnection connection;

        try {
            url = new URL(urlBase);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            String params = QueryFactory.unlock();

            connection.setDoOutput(true);
            connection.setDoInput(true);

            DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(params);
            writer.flush();
            writer.close();

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' to unlock database");
            System.out.println(("\nResponse Code : " + responseCode));

            if (responseCode >= HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                System.out.println(response.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Get a list of airplanes from the server
     * @return The list of airplanes
     */
    public ArrayList<Airplane> getAirplanes() {
        URL url;
        HttpURLConnection connection;
        BufferedReader reader;
        String line;
        StringBuilder result = new StringBuilder();

        String xmlAirplanes;
        ArrayList<Airplane> airplanes;

        try {
            url = new URL(urlBase + QueryFactory.getAirplanes());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", TEAM_NAME);
            int responseCode = connection.getResponseCode();
            if (responseCode >= HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        xmlAirplanes = result.toString();
        airplanes = DaoAirplane.addAll(xmlAirplanes);
        return airplanes;
    }
}
