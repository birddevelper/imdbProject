package mst.shr.imdb.imdbproject.repositories;

import mst.shr.imdb.imdbproject.models.dbModels.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, String> {
}