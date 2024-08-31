package tourism;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import tourism.model.TouristAttraction;
import org.springframework.stereotype.Service;
import tourism.repository.TouristRepository;

import java.util.List;

@Service
@SpringBootApplication
public class TouristGuideApplication {
    private final TouristRepository touristRepository;

    public TouristGuideApplication(TouristRepository touristRepository){
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getTouristAttractionList(){
        return touristRepository.getTouristAttractionList();
    }

    public TouristAttraction createAttraction(TouristAttraction touristAttraction){
        return  touristRepository.createAttraction(touristAttraction);
    }

    public TouristAttraction findAttractionByName(String name){
        TouristAttraction touristAttraction = touristRepository.findAttractionByName(name);
        return touristAttraction;
    }

    public TouristAttraction deleteAttraction(String name){
        TouristAttraction touristAttraction = touristRepository.deleteAttraction(name);
        return touristAttraction;
    }
}
