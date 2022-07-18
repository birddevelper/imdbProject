package mst.shr.imdb.imdbproject.services;

import mst.shr.imdb.imdbproject.models.dbModels.Movie;

import java.util.HashMap;
import java.util.List;


public interface MovieService {


    /**
     * This method gets a genre name as a string and returns best-selling movie title on each year for that genre
     * @param genres    The genres name
     * @return  Dictionary of the Year:Movie-title
     */
    HashMap<Integer, Movie> getGenresBestSellingMovies(String genres);

    /**
     * This method returns list of movies in which both writer and director are same person an is alive
     * @return  List of the Movies
     */
    List<Movie> getMoviesWithOneAlivePersonAsWriterAndDirector();

    /**
     * This method gets two person's uniq id and returns movies that both of them played in
     * @return  List of the Movies
     */
    List<Movie> getCommonMoviesOfTwoActors(String actor1, String actor2);

}
