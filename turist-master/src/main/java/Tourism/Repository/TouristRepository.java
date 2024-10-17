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
    String password = "Fam39ash012002";
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

    public List<String> getCities(){
        return cities;
    }

    public List<String> getTags(){
        return tags;
    }


}
