package de.maritimehack.scrapmap.backend.controller;

import de.maritimehack.scrapmap.backend.entities.PollutionCategory;
import de.maritimehack.scrapmap.backend.entities.PollutionSpot;
import de.maritimehack.scrapmap.backend.entities.User;
import de.maritimehack.scrapmap.backend.repositories.PollutionSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    // This means that this class is a Controller
@RequestMapping(path = "/pollutionSpot") // This means URL's start with /scrapmap (after Application path)
public class PollutionSpotController {
    @Autowired
    private PollutionSpotRepository pollutionSpotRepository;

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewPollutionSpot(@RequestBody PollutionSpot pollutionSpot) {
        pollutionSpotRepository.save(pollutionSpot);
        return "Saved";
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<PollutionSpot> getAllPollutionSpots() {
        // This returns a JSON or XML with the users
        return pollutionSpotRepository.findAll();
    }
}
