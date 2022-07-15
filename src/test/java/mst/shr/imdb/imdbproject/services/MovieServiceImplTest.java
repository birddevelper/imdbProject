package mst.shr.imdb.imdbproject.services;

import mst.shr.imdb.imdbproject.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MovieServiceImplTest {

    @Resource
    MovieServiceImpl movieService;

    @Resource
    MovieRepository movieRepository;

    @BeforeEach
    void setUp() {

    }



    @Test
    void genresBestSellingMovies() {
    }
}