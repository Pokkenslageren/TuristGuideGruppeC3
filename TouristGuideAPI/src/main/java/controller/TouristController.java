package controller;

import org.example.touristguideapi.TouristGuideApiApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.TouristGuideApplication;

@Controller
@RequestMapping("attractions")
public class TouristController {
    private final TouristGuideApplication touristGuideApplication;
    public TouristController(TouristGuideApplication touristGuideApplication){
        this.touristGuideApplication = touristGuideApplication;
    }

}
