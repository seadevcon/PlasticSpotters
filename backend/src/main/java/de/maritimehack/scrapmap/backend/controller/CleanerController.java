package de.maritimehack.scrapmap.backend.controller;

import de.maritimehack.scrapmap.backend.entities.Cleaner;
import de.maritimehack.scrapmap.backend.entities.PollutionCategory;
import de.maritimehack.scrapmap.backend.repositories.CleanerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/cleaner")
public class CleanerController {
    @Autowired
    private CleanerRepository cleanerRepository;

    @GetMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewCleaner(@RequestParam String name, @RequestParam String type, @RequestParam String category, @RequestParam Float lat, @RequestParam Float lng) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Cleaner cleaner = new Cleaner();
        cleaner.setName(name);
        cleaner.setType(type);
        cleaner.setCategory(category);
        cleaner.setLat(lat);
        cleaner.setLng(lng);
        cleanerRepository.save(cleaner);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Cleaner> getAllCleanerss() {
        // This returns a JSON or XML with the users
        return cleanerRepository.findAll();
    }
}
