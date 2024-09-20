package Tourism;

import Tourism.Model.TouristAttraction;
import Tourism.Repository.TouristRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SpringBootApplication
public class TouristGuideApplication {

    private final TouristRepository touristRepository;

    public  TouristGuideApplication(TouristRepository touristRepository){this.touristRepository = touristRepository;}

    public List<TouristAttraction> getTouristAttractionList(){ return touristRepository.getTouristAttractionList();}

    public TouristAttraction getAttractionByName(String name){
        TouristAttraction touristAttraction = touristRepository.getAttractionByName(name);
        return touristAttraction;
    }

    public TouristAttraction deleteAttraction(String name){
        TouristAttraction touristAttraction = touristRepository.deleteAttraction(name);
        return touristAttraction;
    }

}
