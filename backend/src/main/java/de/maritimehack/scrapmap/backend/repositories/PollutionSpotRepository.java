package de.maritimehack.scrapmap.backend.repositories;

import de.maritimehack.scrapmap.backend.entities.PollutionSpot;
import org.springframework.data.repository.CrudRepository;

public interface PollutionSpotRepository extends CrudRepository<PollutionSpot, Integer> {
}
