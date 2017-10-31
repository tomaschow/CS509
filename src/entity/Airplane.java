package entity;

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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxFirst() {
        return maxFirst;
    }

    public void setMaxFirst(int maxFirst) {
        this.maxFirst = maxFirst;
    }

    public int getMaxCoach() {
        return maxCoach;
    }

    public void setMaxCoach(int maxCoach) {
        this.maxCoach = maxCoach;
    }
}
