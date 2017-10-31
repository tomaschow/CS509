package entity;

/**
 * Created by: Tomas on 2017/10/03.
 * code: The unique identifier of the airport
 * timeOffset: The difference in hours between local time and GMT
 * name: The full name of the airport
 */
public class Airport {
    private String code;
    private int timeOffset;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(int timeOffset) {
        this.timeOffset = timeOffset;
    }

}
