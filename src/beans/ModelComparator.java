package beans;

import java.util.Comparator;

public class ModelComparator implements Comparator<Airplane> {

    @Override
    public int compare(Airplane r1, Airplane r2) {
        // TODO Auto-generated method stub
        String name1 = r1.getName();
        String name2 = r2.getName();
        return name1.compareTo(name2);

    }


}
