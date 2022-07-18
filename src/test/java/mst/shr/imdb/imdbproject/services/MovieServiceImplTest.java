package mst.shr.imdb.imdbproject.services;

import mst.shr.imdb.imdbproject.models.dbModels.Movie;
import mst.shr.imdb.imdbproject.repositories.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTest.sql")
class MovieServiceImplTest {

    @Resource
    MovieServiceImpl movieService;

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private MovieGenresRepository movieGenresRepository;

    @Resource
    private MovieCastRepository movieCastRepository;

    @Resource
    private PersonRepository personRepository;

    @Resource
    private RatingRepository ratingRepository;

    @BeforeAll
    static void beforeAll() {

    }




    @Test
    void genresBestSellingMovies() {

       HashMap<Integer, Movie> movieHashMap = movieService.getGenresBestSellingMovies("sport");

       assertEquals("Movie7",movieHashMap.get(1980).getTitle());
       assertEquals("M8",movieHashMap.get(2000).getTitle());
    }

    @Test
    void getMoviesWithOneAlivePersonAsWriterAndDirector() {

        List<Movie> movieList =  movieService.getMoviesWithOneAlivePersonAsWriterAndDirector();
        assertEquals("2",movieList.get(0).getId());
    }

    @Test
    void getCommonMoviesOfTwoActorsWhenTheyHaveCommonMovie(){

        List<Movie> movieList = movieService.getCommonMoviesOfTwoActors("3","4");
        assertEquals("1",movieList.get(0).getId());

    }

    @Test
    void getCommonMoviesOfTwoActorsWhenTheyHaveNoCommonMovie(){

        List<Movie> movieList = movieService.getCommonMoviesOfTwoActors("3","1");
        assertEquals(0,movieList.size());
    }
}