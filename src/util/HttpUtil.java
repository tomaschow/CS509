package util;

import DAO.DaoAirport;
import DAO.DaoFlight;
import entity.Airports;
import entity.Flights;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by: Tomas on 2017/10/03.
 */
public enum HttpUtil {
    INSTANCE;

    private static String urlBase = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";


    public Flights getFlights (String teamName,String date,String code) {

        URL url;
        HttpURLConnection connection;
        BufferedReader reader;
        String line;
        StringBuilder result = new StringBuilder();

        String xmlFlights;
        Flights flights;

        try {
            /**
             * Create an HTTP connection to the server for a GET
             */
            url = new URL(urlBase + QueryFactory.getDepFlights(teamName,date,code));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", teamName);

            /**
             * If response code of SUCCESS read the XML string returned
             * line by line to build the full return string
             */
            int responseCode = connection.getResponseCode();
            if (responseCode >= HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                String encoding = connection.getContentEncoding();
                encoding = (encoding == null ? "UTF-8" : encoding);

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

    public Airports getAirports (String teamName) {

        URL url;
        HttpURLConnection connection;
        BufferedReader reader;
        String line;
        StringBuilder result = new StringBuilder();

        String xmlAirports;
        Airports airports;

        try {
            /**
             * Create an HTTP connection to the server for a GET
             */
            url = new URL(urlBase + QueryFactory.getAirports(teamName));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", teamName);

            /**
             * If response code of SUCCESS read the XML string returned
             * line by line to build the full return string
             */
            int responseCode = connection.getResponseCode();
            if (responseCode >= HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                String encoding = connection.getContentEncoding();
                encoding = (encoding == null ? "UTF-8" : encoding);

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
     * @param teamName is the name of team requesting server lock
     * @return true if the server was locked successfully, else false
     */
    public boolean lock (String teamName) {
        URL url;
        HttpURLConnection connection;

        try {
            url = new URL(urlBase);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", teamName);
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String params = QueryFactory.lock(teamName);

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
            StringBuffer response = new StringBuffer();

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
     * @param teamName is the name of the team holding the lock
     * @return true if the server was successfully unlocked.
     */
    public boolean unlock (String teamName) {
        URL url;
        HttpURLConnection connection;

        try {
            url = new URL(urlBase);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            String params = QueryFactory.unlock(teamName);

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
                StringBuffer response = new StringBuffer();

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                System.out.println(response.toString());
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
//    /**
//     * @param  request  // the raw string request determined in other classes
//     * @return response // The raw XML string
//     */
//    static String doGet(String request) {
//        String finalURL = urlBase + request;
//        String response = "ERROR";
//        try{
//            URL url = new URL(finalURL);
//            URLConnection conn = url.openConnection();
//            int timeout = 12000;
//            conn.setConnectTimeout(timeout);
//            conn.setRequestProperty("Accept-Charset","UTF-8");
//            InputStream is = conn.getInputStream();
//            Scanner scanner = new Scanner(is);
//            //
//            response = scanner.useDelimiter("\\A").next();
//            System.out.println("Data received: "+response);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return response;
//    }
//    static void doPost(){
//        String request = "team=Shoebill&action=lockDB";
////        String request = "team=Shoebill&action=unlockDB";
//        try {
//            URLConnection conn = new URL(urlBase).openConnection();
//            conn.setDoOutput(true);
//            conn.setRequestProperty("Accept-Charset", "UTF-8");
////            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
//            OutputStream os = conn.getOutputStream();
//            os.write(request.getBytes("UTF-8"));
//            InputStream is = conn.getInputStream();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
