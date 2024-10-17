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
        try {
            String cityQuery = "SELECT * FROM attraction_cities";
            Class.forName("com.mysql.jc.jdbc.Driver");
            conn = DriverManager.getConnection(database, username, password);
            if (conn == null) {
                System.out.println("connection not established.");
            }
            PreparedStatement pstmt = conn.prepareStatement(cityQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                cities.add(rs.getString("city"));
                System.out.println(rs.getString("city"));
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
