package service;

import model.TouristAttraction;
import org.springframework.stereotype.Service;
import repository.TouristRepository;

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
}
