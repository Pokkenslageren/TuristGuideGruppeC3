package org.example.touristguideapi;

import tourism.model.TouristAttraction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tourism.repository.TouristRepository;

@SpringBootTest
class TouristGuideApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void removeFromList(){

        TouristRepository touristRepository = new TouristRepository();



        touristRepository.deleteAttraction("tivoli");


        for(TouristAttraction t : touristRepository.getTouristAttractionList()){
            System.out.println(t.getName());
        }


    }

}
