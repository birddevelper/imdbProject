package mst.shr.imdb.imdbproject.controllers;

import mst.shr.imdb.imdbproject.models.responseModels.ApiResponseModel;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// This class handles error and return appropriate response to client
@ControllerAdvice
public class ExceptionHelper {


    // handling IllegalArgumentException
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ApiResponseModel<String>> handleBusinessException(IllegalArgumentException ex) {


        ApiResponseModel<String> apiResponseModel = new ApiResponseModel<>();
        apiResponseModel.setMessage("ERROR");
        apiResponseModel.setDescription(ex.getMessage());

        // send response with 400 status code
        return new ResponseEntity(apiResponseModel, HttpStatus.BAD_REQUEST);


    }

    // handling other Exception
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiResponseModel<String>> handleException(Exception ex) {

        ApiResponseModel<String> apiResponseModel = new ApiResponseModel<>();
        apiResponseModel.setMessage("ERROR");
        apiResponseModel.setDescription(ex.getMessage());

        // send response with 500 status code
        return new ResponseEntity<>(apiResponseModel, HttpStatus.INTERNAL_SERVER_ERROR);


    }

}