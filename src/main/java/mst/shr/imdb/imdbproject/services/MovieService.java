package mst.shr.imdb.imdbproject.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Dictionary;


public interface MovieService {


    /**
     * This method gets a genre name as a string and returns best-selling movie title on each year for that genre
     * @param genres    The genres name
     * @return  Dictionary of the Year:Movie-title
     */
    Dictionary<Integer,String> genresBestSellingMovies(String genres);

}
