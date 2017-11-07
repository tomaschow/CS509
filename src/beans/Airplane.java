package beans;

/**
 * Created by: Tomas on 2017/10/31.
 * manufacturer: The maker of the airplane
 * name: The unique identifier of the airplane
 * maxFirst: The max number of first class seats of this model
 * maxCoach: The max number of coach seats of this model
 */
public class Airplane {
    private String manufacturer;
    private String name;
    private int maxFirst;
    private int maxCoach;

    public Airplane(String name){
        this.name=name;
    }
    public Airplane(){
        manufacturer = "";
        name = "";
        maxFirst = 0;
        maxCoach = 0;
    }

    public Airplane(String manufacturer, String name, int maxFirst, int maxCoach) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.maxFirst = maxFirst;
        this.maxCoach = maxCoach;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int getMaxFirst() {
        return maxFirst;
    }

    public void setMaxFirst(int maxFirst) {
        if(isValidSeat(maxFirst)) {
            this.maxFirst = maxFirst;
        }
        else
            throw new IllegalArgumentException(Integer.toString(maxFirst));
    }

    private int getMaxCoach() {
        return maxCoach;
    }

    public void setMaxCoach(int maxCoach) {
        if(isValidSeat(maxCoach)) {
            this.maxCoach = maxCoach;
        }
        else
            throw new IllegalArgumentException(Integer.toString(maxCoach));
    }
    public boolean isValid(){
        return isValidSeat(this.getMaxCoach()) && isValidSeat(this.getMaxFirst());
    }
    private boolean isValidSeat(int seatNum) {
        return seatNum > 0;
    }
}
