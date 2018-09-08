package de.maritimehack.scrapmap.backend.repositories;

import de.maritimehack.scrapmap.backend.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
