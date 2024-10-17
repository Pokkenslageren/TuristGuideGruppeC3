package Tourism.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TouristRepository rep = new TouristRepository();
        ArrayList<String> test = (ArrayList<String>) rep.getCities();
        System.out.println(test.get(1));
    }
}
