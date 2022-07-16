package mst.shr.imdb.imdbproject.repositories;

import mst.shr.imdb.imdbproject.models.dbModels.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,String> {

    List<Person> findByAliveEquals(boolean isAlive);
}
