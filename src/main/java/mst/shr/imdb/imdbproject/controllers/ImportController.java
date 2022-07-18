package mst.shr.imdb.imdbproject.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import mst.shr.imdb.imdbproject.models.responseModels.ApiResponseModel;
import mst.shr.imdb.imdbproject.services.ImportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/db")
@Tag(name = "Database", description = "Endpoints to work with DB")
public class ImportController {


    private ImportService importService;

    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping( produces = { "application/json" }, consumes = { "multipart/form-data" })
    public ResponseEntity<ApiResponseModel<String>> importDataset(@Parameter(description = "Dataset file") MultipartFile file ) throws IOException, NoSuchAlgorithmException {

        importService.importDataset(file);

        ApiResponseModel<String> apiResponseModel = new ApiResponseModel<>();
        apiResponseModel.setMessage("OK");
        apiResponseModel.setDescription("File uploaded successfully");
        return new ResponseEntity(apiResponseModel, HttpStatus.CREATED);
    }




    }
