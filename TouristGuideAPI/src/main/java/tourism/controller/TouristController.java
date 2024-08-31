package tourism.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import tourism.TouristGuideApplication;

import java.util.*;

@Controller

@RequestMapping("/attractions")
public class TouristController {
    private final TouristGuideApplication touristGuideApplication;


    public TouristController(TouristGuideApplication touristGuideApplication){
        this.touristGuideApplication = touristGuideApplication;
    }

    //MAin der peger på TouristGuideApplication. Dunno om det er rigtigt.
    public static void main(String[] args) {
        SpringApplication.run(TouristGuideApplication.class, args);
    }

    @GetMapping("")
    public ResponseEntity<List<TouristAttraction>> getTouristAttractionList(){
        List<TouristAttraction> touristAttractionList = touristGuideApplication.getTouristAttractionList();
        return new ResponseEntity<>(touristAttractionList, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name){
        TouristAttraction touristAttraction = touristGuideApplication.findAttractionByName(name);
        return new ResponseEntity<>(touristAttraction,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TouristAttraction> addTouristAttraction(@RequestBody TouristAttraction touristAttraction){
        TouristAttraction newTouristAttraction = touristGuideApplication.createAttraction(touristAttraction);
        return new ResponseEntity<>(newTouristAttraction, HttpStatus.CREATED);
    }



}
