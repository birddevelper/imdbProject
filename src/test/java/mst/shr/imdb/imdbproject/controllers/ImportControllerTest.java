package mst.shr.imdb.imdbproject.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureWebClient
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ImportControllerTest {


    @Resource
    private MockMvc mockMvc;

    @Test
    void importDatasetWithValidFileFormat() throws Exception {
        MockMultipartFile validFile = new MockMultipartFile("file", "filename.tsv", "text/plain",
                ("tconst\tdirectors\twriters\n" +
                        "tt0000001\tnm0005690\t\\N\n" +
                        "tt0000007\tnm0005690,nm0374658\t\\N\n" +
                        "tt0000009\tnm0085156\tnm0085156\n" +
                        "tt0000247\tnm0002504,nm0005690,nm2156608\tnm0000636,nm0002504").getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/db/import")
                .file(validFile)).andExpect(status().isCreated())
                .andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    void importDatasetWithInvalidFileFormat() throws Exception {
        MockMultipartFile validFile = new MockMultipartFile("file", "filename.tsv", "text/plain",
                ("Hi How are you?").getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/db/import")
                        .file(validFile)).andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("ERROR"));
    }
}