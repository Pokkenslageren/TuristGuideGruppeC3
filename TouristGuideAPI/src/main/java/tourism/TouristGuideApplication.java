package tourism;

import tourism.model.TouristAttraction;
import org.springframework.stereotype.Service;
import tourism.repository.TouristRepository;

import java.util.List;

@Service
public class TouristGuideApplication {
    private final TouristRepository touristRepository;

    public TouristGuideApplication(TouristRepository touristRepository){
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getTouristAttractionList(){
        return touristRepository.getTouristAttractionList();
    }

    public TouristAttraction createAttraction(String name, String description){
        return  touristRepository.createAttraction(name, description);
    }
}
