package mst.shr.imdb.imdbproject.services;

import mst.shr.imdb.imdbproject.models.dbModels.MovieGenres;
import mst.shr.imdb.imdbproject.models.dbModels.Rating;
import mst.shr.imdb.imdbproject.repositories.*;
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
    private ImportServiceImpl importService;

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



    @BeforeEach
    void setUp() {
    }

    @Test
    void importDatasetWhenTitleBasicFileIsGiven() throws IOException, NoSuchAlgorithmException {
        byte[] file = ("tconst\titleType\tprimaryTitle\toriginalTitle\tisAdult\tstartYear\tendYear\truntimeMinutes\tgenres\n" +
                "tt0000001\tshort\tCarmencita\tCarmencita\t0\t1894\t\\N\t1\tDocumentary,Short\n" +
                "tt0000002\tshort\tLe clown et ses chiens\tLe clown et ses chiens\t0\tNovember 7, 1892\t\\N\t5\tAnimation,Short").getBytes(StandardCharsets.UTF_8);

        MultipartFile multipartFile = new MockMultipartFile("datasetFile.tsv", file);

        importService.importDataset(multipartFile);

        assertEquals(2,movieRepository.findAll().size());
        assertEquals(4,movieGenresRepository.findAll().size());
    }

    @Test
    void importDatasetWhenTitleCrewFileIsGiven() throws IOException, NoSuchAlgorithmException {
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
    void importDatasetWhenTitlePrincipalFileIsGiven() throws IOException, NoSuchAlgorithmException {
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


    @Test
    void importDatasetWhenNameBasicFileIsGiven() throws IOException, NoSuchAlgorithmException {
        byte[] file = ("nconst\tprimaryName\tbirthYear\tdeathYear\tprimaryProfession\tknownForTitles\n" +
                "nm0000001\tFred Astaire\t1899\t1987\tsoundtrack,actor,miscellaneous\ttt0031983,tt0053137,tt0072308,tt0050419\n" +
                "nm0000002\tLauren Bacall\t1924\t2014\tactress,soundtrack\ttt0037382,tt0038355,tt0071877,tt0117057\n" +
                "nm0000003\tBrigitte Bardot\t1934\t\\N\tactress,soundtrack,music_department\ttt0049189,tt0054452,tt0057345,tt0056404\n" +
                "nm0000004\tJohn Belushi\t1949\t1982\tactor,soundtrack,writer\ttt0078723,tt0080455,tt0077975,tt0072562\n" +
                "nm0000005\tIngmar Bergman\t1918\t2007\twriter,director,actor\ttt0050976,tt0050986,tt0060827,tt0083922\n" +
                "nm0000006\tIngrid Bergman\t1915\t1982\tactress,soundtrack,producer\ttt0034583,tt0036855,tt0038109,tt0077711\n" +
                "nm0000047\tSophia Loren\t1934\t\\N\tactress,soundtrack\ttt0076085,tt0060121,tt0058335,tt0054749\n").getBytes(StandardCharsets.UTF_8);

        MultipartFile multipartFile = new MockMultipartFile("datasetFile.tsv", file);

        importService.importDataset(multipartFile);

        assertEquals(2,personRepository.findByAliveEquals(true).size());
        assertEquals(5,personRepository.findByAliveEquals(false).size());
    }

    @Test
    void importDatasetWhenTitleRatingsFileIsGiven() throws IOException, NoSuchAlgorithmException {
        byte[] file = ("tconst\taverageRating\tnumVotes\n" +
                "tt0000001\t5.7\t1893\n" +
                "tt0000002\t5.9\t253\n" +
                "tt0000003\t6.5\t1688\n" +
                "tt0000004\t5.7\t166\n" +
                "tt0000005\t6.2\t2504\n" +
                "tt0000006\t5.1\t170\n" +
                "tt0000007\t5.4\t783\n" +
                "tt0000008\t5.4\t2032\n" +
                "tt0000009\t5.3\t197\n" +
                "tt0000010\t6.9\t6849\n").getBytes(StandardCharsets.UTF_8);

        MultipartFile multipartFile = new MockMultipartFile("datasetFile.tsv", file);

        importService.importDataset(multipartFile);

        assertEquals(10,ratingRepository.findAll().size());
    }

    @Test
    void importDatasetWhenInvalidColumnFormatIsGiven() throws IOException, NoSuchAlgorithmException {

        byte[] file = ("titleId\tordering\ttitle\tregion\tlanguage\ttypes\tattributes\tisOriginalTitle\n" +
                "tt0000001\t1\tКарменсіта\tUA\t\\N\timdbDisplay\t\\N\t0\n" +
                "tt0000001\t2\tCarmencita\tDE\t\\N\t\\N\tliteral title\t0\n" +
                "tt0000001\t3\tCarmencita - spanyol tánc\tHU\t\\N\timdbDisplay\t\\N\t0\n").getBytes(StandardCharsets.UTF_8);

        MultipartFile multipartFile = new MockMultipartFile("datasetFile.tsv", file);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            importService.importDataset(multipartFile);
        });

        String expectedMessage = "Invalid columns format";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


    }

    @Test
    void importDatasetWhenInvalidFileFormatIsGiven() throws IOException, NoSuchAlgorithmException {

        byte[] file = ("Hi How are you").getBytes(StandardCharsets.UTF_8);

        MultipartFile multipartFile = new MockMultipartFile("datasetFile.tsv", file);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            importService.importDataset(multipartFile);
        });

        String expectedMessage = "Invalid file format";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


    }

}