package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by: Tomas on 2017/10/03.
 */
class HttpUtil {
    static String url1 = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";

    /**
     * @param  request  // the raw string request determined in other classes
     * @return response // The raw XML string
     */
    static String doGet(String request) {
        String finalURL = url1 + request;
        String response = "ERROR";
        try{
            URL url = new URL(finalURL);
            URLConnection conn = url.openConnection();
            int timeout = 12000;
            conn.setConnectTimeout(timeout);
            conn.setRequestProperty("Accept-Charset","UTF-8");
            InputStream is = conn.getInputStream();
            Scanner scanner = new Scanner(is);
            //
            response = scanner.useDelimiter("\\A").next();
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
//    static void doPost(){
//        String request = "team=Shoebill&action=lockDB";
////        String request = "team=Shoebill&action=unlockDB";
//        try {
//            URLConnection conn = new URL(url1).openConnection();
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
