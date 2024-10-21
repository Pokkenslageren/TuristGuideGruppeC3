package Tourism.Repository;

import Tourism.Model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.sql.*;

@Repository
public class TouristRepository {

    List<TouristAttraction> touristAttractionList = new ArrayList<>();
    List<String> cities = new ArrayList<>();
    List<String> tags = new ArrayList<>();
    String database = "jdbc:mysql://localhost:3306/tourist_attractions";
    String username = "root";
    String password = "Illcosby91";
    Connection conn;

    public TouristRepository(){
        String cityQuery = "SELECT * FROM attraction_cities";
        String pretagQuery = "SELECT * FROM attraction_pretags";
        String descriptionQuery = "SELECT * FROM attraction_description";
        String tagQuery = "SELECT * FROM attraction_tags";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, username, password);
            if (conn == null) {
                System.out.println("connection not established.");
            }
            PreparedStatement pstmtCities = conn.prepareStatement(cityQuery);
            ResultSet rsCities = pstmtCities.executeQuery();
            while (rsCities.next()) {
                cities.add(rsCities.getString("city"));

            }
            PreparedStatement pstmtPretag = conn.prepareStatement(pretagQuery);
            ResultSet rsPretag = pstmtPretag.executeQuery();
            while (rsPretag.next()) {
                tags.add(rsPretag.getString("tag"));
                System.out.println(rsPretag.getString("tag"));
            }
            PreparedStatement pstmtDescription = conn.prepareStatement(descriptionQuery);
            ResultSet rsDescription = pstmtDescription.executeQuery();
            while (rsDescription.next()) {
                String name = rsDescription.getString("name");
                String description = rsDescription.getString("description");
                String city = rsDescription.getString("city");
                ArrayList<String> tags = new ArrayList<>();
                TouristAttraction touristAttraction = new TouristAttraction(name, description, city, tags);
                touristAttractionList.add(touristAttraction);
            }
            PreparedStatement pstmtTag = conn.prepareStatement(tagQuery);
            ResultSet rsTag = pstmtTag.executeQuery();
            ArrayList<ArrayList<String>> doubleString = new ArrayList<>();
            while (rsTag.next()) {
                ArrayList<String> jamesTags = new ArrayList<>();
                String tag1 = rsTag.getString("tag1");
                String tag2 = rsTag.getString("tag2");
                String tag3 = rsTag.getString("tag3");
                String tag4 = rsTag.getString("tag4");
                jamesTags.add(tag1);
                jamesTags.add(tag2);
                jamesTags.add(tag3);
                jamesTags.add(tag4);
                doubleString.add(jamesTags);
            }
            for (int i = 0; i <= touristAttractionList.size(); i++) {
                touristAttractionList.get(i).setTags(doubleString.get(i));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
     * CRUD method to read an attraction from the database, based on input name
     * @param name input to search for database entry
     * @return a corresponding tourist attraction
     */
    public TouristAttraction readAttractionByName(String name){
        String descriptionQuery = "SELECT * FROM attraction_description WHERE name = ?;";
        String tagsQuery = "SELECT * FROM attraction_tags WHERE name = ?;";
        try{
            ArrayList<TouristAttraction> tempList = new ArrayList<>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, username, password);
            if (conn == null) {
                System.out.println("connection not established.");
            }
            PreparedStatement pstmtDescription = conn.prepareStatement(descriptionQuery);
            pstmtDescription.setString(1,name);
            ResultSet rsDescription = pstmtDescription.executeQuery();
            while(rsDescription.next()){
                String attractionName = rsDescription.getString("name");
                String attractionDescription = rsDescription.getString("description");
                String attractionCity = rsDescription.getString("city");
                ArrayList<String> attractionTags = new ArrayList<>();
                TouristAttraction touristAttraction = new TouristAttraction(attractionName,attractionDescription,attractionCity,attractionTags);
                tempList.add(touristAttraction);
            }
            PreparedStatement pstmtTags = conn.prepareStatement(tagsQuery);
            pstmtTags.setString(1,name);
            ResultSet rsTags = pstmtTags.executeQuery();
            while(rsTags.next()){
                ArrayList<String> attractionTags = new ArrayList<>();
                attractionTags.add(rsTags.getString("tag1"));
                attractionTags.add(rsTags.getString("tag2"));
                attractionTags.add(rsTags.getString("tag3"));
                attractionTags.add(rsTags.getString("tag4"));
                tempList.get(0).setTags(attractionTags);
            }
            return tempList.get(0);
        }
        catch (Exception e){
            e.getMessage();
        }
        return null;
    }


    /**
     * CRUD method to remove a tourist attraction from the list
     * @param name of the attractions
     */
    public TouristAttraction deleteAttraction(String name){
        String deleteQueryDescription = "DELETE FROM attraction_description WHERE name = ?";
        String deleteQueryTags = "DELETE FROM attraction_tags WHERE name = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, username, password);
            if (conn == null) {
                System.out.println("connection not established.");
            }
            PreparedStatement pstmt = conn.prepareStatement(deleteQueryDescription);
            pstmt.setString(1, name);
            PreparedStatement pstmt1 = conn.prepareStatement(deleteQueryTags);
            pstmt1.setString(1, name);

            int rowDeleted = pstmt.executeUpdate();
            int rowDeleted1 = pstmt1.executeUpdate();

            if(rowDeleted > 0 && rowDeleted1 > 0) {
                System.out.println("Row deleted successfully.");
            } else {
                System.out.println("Row deletion failed. Name not found");
            }

        }
        catch (Exception e) {
            System.out.println("Error deleting profile" + e.getMessage());
        }


        /*Iterator<TouristAttraction> iterator = touristAttractionList.iterator();
        while(iterator.hasNext()){
            TouristAttraction touristAttraction = iterator.next();
            if(touristAttraction.getName().equals(name)){
                iterator.remove();
                return touristAttraction;
            }
        }
        return null;*/
        return null;
    }

    /**
     * CRUD method to create a new tourist attraction
     * @param touristAttraction the attraction to be created
     */
    public void saveAttraction(TouristAttraction touristAttraction){
        getTouristAttractionList().add(touristAttraction);
        String attractionName = touristAttraction.getName();
        String attractionDescription = touristAttraction.getDescription();
        String attractionCity = touristAttraction.getCity();
        List<String> attractionTags = touristAttraction.getTags();
        String saveQueryDescription = "INSERT INTO `attraction_description` (`name`,`description`,`city`)  VALUES (?,?,?);";
        String saveQueryTags = "INSERT INTO `attraction_tags` (`name`,`city`,`tag1`,`tag2`,`tag3`,`tag4` ) VALUES (?,?,?,?,?,?);";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, username, password);
            if (conn == null) {
                System.out.println("connection not established.");
            }
            PreparedStatement psmtSaveDescription = conn.prepareStatement(saveQueryDescription);
            psmtSaveDescription.setString(1,attractionName);
            psmtSaveDescription.setString(2,attractionDescription);
            psmtSaveDescription.setString(3,attractionCity);
            PreparedStatement psmtSaveTags = conn.prepareStatement(saveQueryTags);
            psmtSaveTags.setString(1,attractionName);
            psmtSaveTags.setString(2,attractionCity);
            psmtSaveTags.setString(3,attractionTags.get(0));
            psmtSaveTags.setString(4,attractionTags.get(1));
            psmtSaveTags.setString(5,attractionTags.get(2));
            psmtSaveTags.setString(6,attractionTags.get(3));

            psmtSaveDescription.executeUpdate();
            psmtSaveTags.executeUpdate();

        }
        catch (Exception e){
            System.out.println("Error: Could not insert attraction" + e.getMessage());
        }
    }

    /**
     * CRUD method to update an attraction
     * @param touristAttraction The tourist attraction to be updated
     * @param name Finds the given attraction by name
     */
    public void updateAttraction(TouristAttraction touristAttraction, String name) {
        String updateDescriptionQuery = "UPDATE `attraction_description` SET name = ?, description = ?, city = ? WHERE name = ?;";
        String updateTagsQuery = "UPDATE attraction_tags SET tag1 = ?, tag2 = ?, tag3 = ?, tag4 = ? WHERE name = ?;";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(database, username, password);
            if (conn == null) {
                System.out.println("connection not established.");
            }
            PreparedStatement psmtUpdateDescription = conn.prepareStatement(updateDescriptionQuery);
            psmtUpdateDescription.setString(1, touristAttraction.getName());
            psmtUpdateDescription.setString(2,touristAttraction.getDescription());
            psmtUpdateDescription.setString(3,touristAttraction.getCity());
            psmtUpdateDescription.setString(4,name);
            psmtUpdateDescription.executeUpdate();
            PreparedStatement psmtUpdateTags = conn.prepareStatement(updateTagsQuery);
            psmtUpdateTags.setString(1,touristAttraction.getTags().get(0));
            psmtUpdateTags.setString(2,touristAttraction.getTags().get(1));
            psmtUpdateTags.setString(3,touristAttraction.getTags().get(2));
            psmtUpdateTags.setString(4,touristAttraction.getTags().get(3));
            psmtUpdateTags.setString(5, name);
            psmtUpdateTags.executeUpdate();
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    public List<String> getCities(){
        return cities;
    }

    public List<String> getTags(){
        return tags;
    }



}
