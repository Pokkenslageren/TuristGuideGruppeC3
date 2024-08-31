package org.example.touristguideapi;

import model.TouristAttraction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import repository.TouristRepository;

@SpringBootTest
class TouristGuideApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void removeFromList(){

        TouristRepository touristRepository = new TouristRepository();

        touristRepository.createAttraction("rundetårn","tårn i købmagergade");

        touristRepository.deleteAttraction("tivoli");


        for(TouristAttraction t : touristRepository.getTouristAttractionList()){
            System.out.println(t.getName());
        }


    }

}
