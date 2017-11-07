package beans;

import util.Saps;

/**
 * Created by: Tomas on 2017/10/03.
 * code: The unique identifier of the airport
 * timeOffset: The difference in hours between local time and GMT
 * name: The full name of the airport
 */
public class Airport {
    private String code;
    private String name;
    private double latitude;
    private double longitude;

    public Airport(){
        code = "";
        name = "";
        latitude = Double.MAX_VALUE;
        longitude = Double.MAX_VALUE;
    }

    public Airport(String name, String code, double latitude, double longitude) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException(name);
        }
        if (!isValidCode(code)) {
            throw new IllegalArgumentException(code);
        }
        if (!isValidLatitude(latitude)) {
            throw new IllegalArgumentException(Double.toString(latitude));
        }
        if (!isValidLongitude(longitude)) {
            throw new IllegalArgumentException(Double.toString(longitude));
        }
        this.name = name;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(isValidName(name)) {
            this.name = name;
        }
        else
            throw new IllegalArgumentException(name);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if(isValidCode(code)) {
            this.code = code;
        }
        else
            throw new IllegalArgumentException(code);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        if(isValidLatitude(latitude)) {
            this.latitude = latitude;
        }
        else
            throw new IllegalArgumentException(Double.toString(latitude));
    }
    public void setLatitude(String latitude) {
        if(isValidLatitude(latitude)) {
            this.latitude = Double.parseDouble(latitude);
        }
        else
            throw new IllegalArgumentException(latitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        if(isValidLongitude(longitude)) {
            this.longitude = longitude;
        }
        else
            throw new IllegalArgumentException(Double.toString(longitude));
    }
    public void setLongitude(String longitude) {
        if(isValidLongitude(longitude)) {
            this.longitude = Double.parseDouble(longitude);
        }
        else
            throw new IllegalArgumentException(longitude);
    }

    public boolean isValid() {

        // If the name isn't valid, the object isn't valid
        if (!isValidName(name))
            return false;

        // If we don't have a 3 character code, object isn't valid
        if (!isValidCode(code))
            return false;

        // Verify latitude and longitude are within range
        return isValidLatitude(latitude) && isValidLongitude(longitude);
    }
    private boolean isValidName(String name) {
        return (name != null) && (name != "");
    }

    private boolean isValidCode(String code) {
        return (code != null) && (code.length() == 3);
    }

    private boolean isValidLatitude(double latitude) {
        return (!(latitude > Saps.MAX_LATITUDE)) && (!(latitude < Saps.MIN_LATITUDE));
    }
    private boolean isValidLatitude(String latitude) {
        double lat;
        try {
            lat = Double.parseDouble(latitude);
        } catch (NullPointerException | NumberFormatException ex) {
            return false;
        }
        return isValidLatitude(lat);
    }

    private boolean isValidLongitude(double longitude) {
        return (!(longitude > Saps.MAX_LONGITUDE)) && (!(longitude < Saps.MIN_LONGITUDE));
    }
    private boolean isValidLongitude(String longitude) {
        double lon;
        try {
            lon = Double.parseDouble(longitude);
        } catch (NullPointerException | NumberFormatException ex) {
            return false;
        }
        return isValidLongitude(lon);
    }
}
