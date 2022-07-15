package mst.shr.imdb.imdbproject.services;

import mst.shr.imdb.imdbproject.models.dbModels.MovieGenres;
import mst.shr.imdb.imdbproject.repositories.MovieCastRepository;
import mst.shr.imdb.imdbproject.repositories.MovieGenresRepository;
import mst.shr.imdb.imdbproject.repositories.MovieRepository;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ImportServiceImplTest {

    @Resource
    ImportServiceImpl importService;

    @Resource
    MovieRepository movieRepository;

    @Resource
    MovieGenresRepository movieGenresRepository;

    @Resource
    MovieCastRepository movieCastRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void importDatasetWhenTitleBasicIsGiven() throws IOException, NoSuchAlgorithmException {
        byte[] file = ("tconst\titleType\tprimaryTitle\toriginalTitle\tisAdult\tstartYear\tendYear\truntimeMinutes\tgenres\n" +
                "tt0000001\tshort\tCarmencita\tCarmencita\t0\t1894\t\\N\t1\tDocumentary,Short\n" +
                "tt0000002\tshort\tLe clown et ses chiens\tLe clown et ses chiens\t0\t1892\t\\N\t5\tAnimation,Short").getBytes(StandardCharsets.UTF_8);

        MultipartFile multipartFile = new MockMultipartFile("datasetFile.tsv", file);

        importService.importDataset(multipartFile);

        assertEquals(2,movieRepository.findAll().size());
        assertEquals(4,movieGenresRepository.findAll().size());
    }

    @Test
    void importDatasetWhenTitleCrewIsGiven() throws IOException, NoSuchAlgorithmException {
        byte[] file = ("tconst\tdirectors\twriters\n" +
                "tt0000001\tnm0005690\t\\N\n" +
                "tt0000007\tnm0005690,nm0374658\t\\N\n" +
                "tt0000009\tnm0085156\tnm0085156\n" +
                "tt0000247\tnm0002504,nm0005690,nm2156608\tnm0000636,nm0002504").getBytes(StandardCharsets.UTF_8);

        MultipartFile multipartFile = new MockMultipartFile("datasetFile.tsv", file);

        importService.importDataset(multipartFile);

        assertEquals(10,movieCastRepository.findAll().size());
    }

    @Test
    void importDatasetWhenTitlePrincipalIsGiven() throws IOException, NoSuchAlgorithmException {
        byte[] file = ("tconst\tordering\tnconst\tcategory\tjob\tcharacters\n" +
                "tt0000001\t1\tnm1588970\tself\t\\N\t[\"Self\"]\n" +
                "tt0000001\t2\tnm0005690\tdirector\t\\N\t\\N\n" +
                "tt0000017\t1\tnm3691272\tactor\t\\N\t[\"The boy\"]\n" +
                "tt0000017\t2\tnm3692829\tactress\t\\N\t[\"The girl\"]\n" +
                "tt0000062\t1\tnm0617588\tdirector\t\\N\t\\N\n" +
                "tt0000063\t1\tnm0617588\tactor\t\\N\t[\"Georges Mأ©liأ¨s\"]\n" +
                "tt0000067\t1\tnm0617588\tdirector\t\\N\t\\N\n" +
                "tt0000075\t1\tnm0194945\tactress\t\\N\t[\"Woman\"]\n" +
                "tt0000083\t1\tnm0617588\tdirector\t\\N\t\\N\n" +
                "tt0000089\t3\tnm0525910\tproducer\tproducer\t\\N\n" +
                "tt0000090\t1\tnm0617588\tdirector\t\\N\t\\N\n" +
                "tt0000091\t1\tnm0194945\tactress\t\\N\t[\"Young woman\"]\n" +
                "tt0000091\t2\tnm6170115\tactor\t\\N\t[\"Mephistopheles\"]\n" +
                "tt0000091\t3\tnm0617588\tactor\t\\N\t[\"Mephistopheles\"]").getBytes(StandardCharsets.UTF_8);

        MultipartFile multipartFile = new MockMultipartFile("datasetFile.tsv", file);

        importService.importDataset(multipartFile);

        assertEquals(7,movieCastRepository.findAll().size());
    }

}