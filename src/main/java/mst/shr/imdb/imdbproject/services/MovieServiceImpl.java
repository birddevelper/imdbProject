package mst.shr.imdb.imdbproject.services;

import mst.shr.imdb.imdbproject.models.dbModels.Movie;
import mst.shr.imdb.imdbproject.repositories.MovieRepository;
import mst.shr.imdb.imdbproject.utilities.FileUtilities;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import static java.lang.Integer.*;

@Service
public class MovieServiceImpl implements MovieService{


    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }




    /**
     * This method gets a genre name as a string and returns best-selling movie title on each year for that genre
     *
     * @param genres The genres name
     * @return Dictionary of the Year:Movie-title
     */
    @Override
    public Dictionary<Integer, String> genresBestSellingMovies(String genres) {
        return null;
    }






}
