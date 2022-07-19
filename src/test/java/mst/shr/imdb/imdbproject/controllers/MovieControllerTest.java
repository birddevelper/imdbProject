package mst.shr.imdb.imdbproject.controllers;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.annotation.DirtiesContext;
        import org.springframework.test.context.ActiveProfiles;
        import org.springframework.test.context.jdbc.Sql;
        import org.springframework.test.web.servlet.MockMvc;

        import javax.annotation.Resource;
        import javax.sql.DataSource;

        import java.sql.SQLException;

        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/*
//@WebAppConfiguration() //"file:src/test/resources"
@ContextConfiguration( classes = { JpaConfig.class }) // loader = AnnotationConfigWebContextLoader.class,

*/

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureWebClient
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTest.sql")
class MovieControllerTest {


    @Resource
    private MockMvc mockMvc;





    @Test
    void getGenresBestSellingMovies() throws Exception {

        mockMvc.perform(get("/api/queries/genresBestSellingMovies?genres=sport")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.records.*").exists());



    }

    @Test
    void getMoviesWithOneAlivePersonAsWriterAndDirector() throws Exception {

        mockMvc.perform(get("/api/queries/moviesWithOneAlivePersonAsWriterAndDirector")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.records[0].id").value("2"));



    }

    @Test
    void getCommonMoviesOfTwoActorsWhenTheyHaveCommonMovie() throws Exception {

        mockMvc.perform(get("/api/queries/commonMoviesOfTwoActors?actor1=3&actor2=4")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.records[0].id").value("1"));



    }

    @Test
    void getCommonMoviesOfTwoActorsWhenTheyHaveNoCommonMovie() throws Exception {

        mockMvc.perform(get("/api/queries/commonMoviesOfTwoActors?actor1=3&actor2=1")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.description").value("Query executed successfully, but found no records"));



    }




}