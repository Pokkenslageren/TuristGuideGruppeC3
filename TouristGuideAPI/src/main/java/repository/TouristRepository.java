package repository;

import model.TouristAttraction;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class TouristRepository{

    List<TouristAttraction> touristAttractionList;


    public TouristRepository(){
        this.touristAttractionList = new ArrayList<>();
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
     * @param name of the attraction
     * @param description of the attraction
     */
    public void createAttraction(String name, String description){
        touristAttractionList.add(new TouristAttraction(name, description));
    }

    public void readAttraction(String name){
        Iterator<TouristAttraction> itr = touristAttractionList.iterator();
        while(itr.hasNext()){
            TouristAttraction touristAttraction = itr.next();
            if(touristAttraction.getName().equals(name)){
                System.out.println(touristAttraction); //NB!
            }
        }
    }

    //public void updateAttraction()


    /**
     * CRUD method to remove a tourist attraction from the list
     * @param name of the attractions
     */
    public void deleteAttraction(String name){
        Iterator<TouristAttraction> itr = touristAttractionList.iterator();
        while(itr.hasNext()){
            TouristAttraction touristAttraction = itr.next();
            if(touristAttraction.getName().equals(name)){
                itr.remove();
            }
        }
    }
}
