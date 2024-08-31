package tourism.controller;

import tourism.model.TouristAttraction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tourism.TouristGuideApplication;

import java.util.*;

@Controller

@RequestMapping("/attractions")
public class TouristController {
    private final TouristGuideApplication touristGuideApplication;
    public TouristController(TouristGuideApplication touristGuideApplication){
        this.touristGuideApplication = touristGuideApplication;
    }

    @GetMapping("")
    public ResponseEntity<List<TouristAttraction>> getTouristAttractionList(){
        List<TouristAttraction> touristAttractionList = touristGuideApplication.getTouristAttractionList();
        return new ResponseEntity<>(touristAttractionList, HttpStatus.OK);
    }



}
