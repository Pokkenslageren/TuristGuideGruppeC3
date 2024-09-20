package Tourism.Repository;

import Tourism.Model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class TouristRepository {

    List<TouristAttraction> touristAttractionList = new ArrayList<>();
    List<String> cities = new ArrayList<>();
    List<String> tags = new ArrayList<>();

    public TouristRepository(){
        touristAttractionList.add(new TouristAttraction("tivoli","Forlystelsespark i København K","København",List.of("Forlystelser","Børnevenlig")));
        touristAttractionList.add(new TouristAttraction("smk", "Kunstmuseum","København", List.of("Kunst", "Museum")));
        touristAttractionList.add(new TouristAttraction("viggos", "Værtshus i Odense midtby", "Odense", List.of("Øl", "God service")));
        touristAttractionList.add(new TouristAttraction("zoo", "Zoologisk have i KBH V", "København", List.of("Børnevenlig","Dyr")));

        cities.add("København");
        cities.add("Odense");
        cities.add("Århus");

        tags.add("Børnevenlig");
        tags.add("Billig");
        tags.add("Kunst");
        tags.add("Dyr");
        tags.add("Kørestolsbruger");
    }

    /**
     * Getter for the list of tourist attractions
     * @return list of tourist attractions
     */
    public List<TouristAttraction> getTouristAttractionList() {
        return touristAttractionList;
    }

    /**
     * Finds the relevant attraction, given the name parameter
     * @param name The name og the attraction to search for
     * @return The relevant attraction
     */
    public TouristAttraction getAttractionByName(String name){
        for (TouristAttraction t : touristAttractionList){
            if(t.getName().equals(name)){
                return t;
            }
        }
        return null;
    }


    /**
     * CRUD method to remove a tourist attraction from the list
     * @param name of the attractions
     */
    public TouristAttraction deleteAttraction(String name){
        Iterator<TouristAttraction> iterator = touristAttractionList.iterator();
        while(iterator.hasNext()){
            TouristAttraction touristAttraction = iterator.next();
            if(touristAttraction.getName().equals(name)){
                iterator.remove();
                return touristAttraction;
            }
        }
        return null;
    }

    public List<String> getCities(){
        return cities;
    }

    public List<String> getTags(){
        return tags;
    }


}
