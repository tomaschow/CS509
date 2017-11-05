package util;

/**
 * Created by: Tomas on 2017/09/24.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(HttpUtil.INSTANCE.getFlights("Shoebill", "2017_12_10", "BOS"));
    }
}
