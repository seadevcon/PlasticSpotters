package de.maritimehack.scrapmap.backend.controller;

import de.maritimehack.scrapmap.backend.entities.Cleaner;
import de.maritimehack.scrapmap.backend.entities.PollutionCategory;
import de.maritimehack.scrapmap.backend.repositories.CleanerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/cleaner")
public class CleanerController {
    @Autowired
    private CleanerRepository cleanerRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewCleaner(@RequestBody Cleaner cleaner) {
        cleanerRepository.save(cleaner);
        return "Saved";
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Cleaner> getAllCleanerss() {
        // This returns a JSON or XML with the users
        return cleanerRepository.findAll();
    }
}
