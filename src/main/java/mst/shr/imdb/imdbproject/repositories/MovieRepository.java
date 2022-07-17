package mst.shr.imdb.imdbproject.repositories;

import mst.shr.imdb.imdbproject.models.dbModels.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {

    // This method runs database query to retrieve movies that has writer = director
    @Query(nativeQuery = true,
            value = "SELECT movie.* FROM " +
                    "(SELECT movie_id, person_id FROM movie_cast WHERE role = 0) tbl_director " +
                    "INNER JOIN (SELECT movie_id, person_id FROM movie_cast WHERE role = 1) tbl_writer" +
                    "ON (tbl_director.movie_id = tbl_writer.movie_id AND " +
                    "tbl_director.person_id = tbl_writer.person_id )" +
                    "INNER JOIN movie ON (tbl_director.movie_id = movie.id) " +
                    "INNER JOIN person ON (tbl_writer.person_id = person.id)" +
                    "WHERE person.alive = 1")
    List<Movie> findMoviesWithSameDirectorAndWriter();


    // This method retrieves best selling movies of the given genres
    @Query(nativeQuery = true,
            value = "SELECT movie.* " +
                    "FROM movie " +
                    "INNER JOIN rating ON movie.id = rating.movie_id " +
                    "INNER JOIN (" +
                    "SELECT movie.`release_year` AS `release_year`, max( rating.average_rate ) AS mxrt " +
                    "FROM movie " +
                    "INNER JOIN movie_genres ON movie.id = movie_genres.movie_id " +
                    "INNER JOIN rating ON movie.id = rating.movie_id " +
                    "WHERE  movie_genres.genres = :genresName " +
                    "GROUP BY  movie.`release_year`  ) max_rate_movie " +
                    "ON ( movie.`release_year` = max_rate_movie.`release_year` AND rating.average_rate = max_rate_movie.mxrt ) " +
                    "ORDER BY movie.`release_year` ASC")
    List<Movie> findGenresBestSellings(@Param("genresName") String genres);

}
