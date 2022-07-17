package mst.shr.imdb.imdbproject.services;

import mst.shr.imdb.imdbproject.models.dbModels.Movie;
import mst.shr.imdb.imdbproject.repositories.MovieCastRepository;
import mst.shr.imdb.imdbproject.repositories.MovieGenresRepository;
import mst.shr.imdb.imdbproject.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{


    private MovieRepository movieRepository;

    private MovieCastRepository movieCastRepository;

    private MovieGenresRepository movieGenresRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieCastRepository movieCastRepository, MovieGenresRepository movieGenresRepository) {
        this.movieRepository = movieRepository;
        this.movieCastRepository = movieCastRepository;
        this.movieGenresRepository = movieGenresRepository;
    }


    /**
     * This method gets a genre name as a string and returns best-selling movie title on each year for that genre
     *
     * @param genres The genres name
     * @return Dictionary of the Year:Movie-title
     */
    @Override
    public HashMap<Integer, Movie> getGenresBestSellingMovies(String genres) {
        List<Movie> movies  = movieRepository.findGenresBestSellings(genres);
        HashMap<Integer,Movie> movieHashMap = new HashMap<>();
        movies.forEach(movie -> movieHashMap.put(movie.getReleaseYear(),movie));

        return movieHashMap;

    }

    /**
     * This method returns list of movies in which both writer and director are same person
     *
     * @return List of the Movies
     */
    @Override
    public List<Movie> getMoviesWithOneAlivePersonAsWriterAndDirector() {
        List<Movie> movies = movieRepository.findMoviesWithSameDirectorAndWriter();
        return movies;
    }

    /**
     * This method gets to person's name
     *
     * @param actor1
     * @param actor2
     * @return List of the Movies
     */
    @Override
    public List<Movie> getCommonMoviesOfTwoActors(String actor1, String actor2) {
        return null;
    }
}
