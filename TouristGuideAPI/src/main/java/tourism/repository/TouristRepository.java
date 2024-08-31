package tourism.repository;

import tourism.model.TouristAttraction;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class TouristRepository{

    List<TouristAttraction> touristAttractionList = new ArrayList<>();


    public TouristRepository(){
        //this.touristAttractionList = new ArrayList<>();
        touristAttractionList.add(new TouristAttraction("operaen", "operahus på refshaleøen"));
        touristAttractionList.add(new TouristAttraction("zoo", "zoologisk have i københavn v"));
        touristAttractionList.add(new TouristAttraction("tivoli", "Forlystelsespark i københavn"));
    }

    //TODO: CRUD metoder: create, remove, update, og remove elementer (altså attraktioner fra listen).

    /**
     * Getter for the list of tourist attractions
     * @return list of tourist attractions
     */
    public List<TouristAttraction> getTouristAttractionList(){
        return touristAttractionList;
    }

    /**
     * CRUD method to add a tourist attraction to the list, given name and description
     * @param touristAttraction
     */
    public TouristAttraction createAttraction(TouristAttraction touristAttraction){
        touristAttractionList.add(touristAttraction);
        return touristAttraction;
    }

    public TouristAttraction findAttractionByName(String name){
        for(TouristAttraction t : touristAttractionList){
            if(t.getName().equals(name)){
                return t;
            }
        }
        return null;
    }

    public TouristAttraction updateAttraction()


    /**
     * CRUD method to remove a tourist attraction from the list
     * @param name of the attractions
     */
    public TouristAttraction deleteAttraction(String name){
        Iterator<TouristAttraction> itr = touristAttractionList.iterator();
        while(itr.hasNext()){
            TouristAttraction touristAttraction = itr.next();
            if(touristAttraction.getName().equals(name)){
                itr.remove();
                return touristAttraction;
            }
        }
        return  null;
    }
}
