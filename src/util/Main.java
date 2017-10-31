package util;

/**
 * Created by: Tomas on 2017/09/24.
 */
public class Main {
    public static void main(String[] args) {
        String request = "?team=Shoebill&action=list&list_type=departing&airport=BOS&day=2017_12_10";
        HttpUtil.doGet(request);

    }
}
