package Tourism.Model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name, description, city;
    List<String> tags;

    public TouristAttraction(){};

    public TouristAttraction(String name, String description, String city, List<String> tags){
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() { return city; }

    public List<String> getTags() { return tags; }



    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTag(String tag) { tags.add(tag); }

    public void setTags(ArrayList<String> tags) { this.tags = tags; }

}