package mst.shr.imdb.imdbproject.repositories;

import mst.shr.imdb.imdbproject.models.dbModels.Movie;
import mst.shr.imdb.imdbproject.models.dbModels.MovieCast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieCastRepository extends JpaRepository<MovieCast,Integer> {


}
