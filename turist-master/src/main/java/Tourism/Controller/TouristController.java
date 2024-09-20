package Tourism.Controller;

import Tourism.Model.TouristAttraction;
import Tourism.Repository.TouristRepository;
import Tourism.TouristGuideApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("")
public class TouristController {

    private final TouristGuideApplication touristGuideApplication;
    private final TouristRepository touristRepository = new TouristRepository();

    public TouristController(TouristGuideApplication touristGuideApplication){
        this.touristGuideApplication = touristGuideApplication;
    }

    public static void main(String[] args) {
        SpringApplication.run(TouristGuideApplication.class, args);
    }

    @GetMapping("/attractions")
    public String getAttractions(Model model){
        model.addAttribute("something", "Turistattraktioner:");
        model.addAttribute("attractions",touristGuideApplication.getTouristAttractionList());

        return "attractionsList";
    }

    @GetMapping("/attractions/{name}")
    public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name){
        TouristAttraction touristAttraction = touristGuideApplication.getAttractionByName(name);
        return new ResponseEntity<>(touristAttraction, HttpStatus.OK);
    }

    @GetMapping("/attractions/{name}/tags")
    public String getAttractionsTagsByName(@PathVariable String name, Model model){
        List<TouristAttraction> touristAttractionList = touristGuideApplication.getTouristAttractionList();
        TouristAttraction touristAttraction = touristGuideApplication.getAttractionByName(name);
        model.addAttribute("attractionName",name );
        model.addAttribute("attraction", touristAttraction);
        model.addAttribute("attractions", touristAttractionList);
        return "tags";
    }

    @GetMapping("/attractions/add")
    public String addAttractions(Model model){
        TouristAttraction touristAttraction = new TouristAttraction();

        model.addAttribute("touristAttraction", touristAttraction);
        model.addAttribute("tags",touristRepository.getTags());
        model.addAttribute("cities", touristRepository.getCities());
        return "saveAttraction";
    }

    @PostMapping("/attractions/save")
    public String saveAttraction(@ModelAttribute TouristAttraction touristAttraction){
        touristGuideApplication.getTouristAttractionList().add(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/attractions/{name}/edit")
    public String editAttraction(@PathVariable String name, Model model){
        model.addAttribute("touristAttraction", touristGuideApplication.getAttractionByName(name));
        model.addAttribute("ord", touristRepository.getTags());
        model.addAttribute("cities", touristRepository.getCities());
        touristGuideApplication.getTouristAttractionList().remove(touristGuideApplication.getAttractionByName(name));
        return "updateAttraction";
    }

    @PostMapping("/attractions/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction){
        touristGuideApplication.getTouristAttractionList().add(touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/attractions/{name}/delete")
/*    public ResponseEntity<TouristAttraction> removeTouristAttraction(@PathVariable String name){
        TouristAttraction touristAttraction = touristGuideApplication.deleteAttraction(name);
        return new ResponseEntity<>(touristAttraction, HttpStatus.OK);
    }*/
    public String deleteTouristAttraction(@PathVariable String name){
        touristGuideApplication.deleteAttraction(name);
        return "redirect:/attractions";
    }
}
