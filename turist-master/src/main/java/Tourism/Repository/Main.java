package Tourism.Repository;

import Tourism.Model.TouristAttraction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        TouristRepository rep = new TouristRepository();
        //ArrayList<String> ehehe = (ArrayList<String>) rep.getCities();
        List<TouristAttraction> test = rep.getTouristAttractionList();
        System.out.println(test.size());
    }
}
