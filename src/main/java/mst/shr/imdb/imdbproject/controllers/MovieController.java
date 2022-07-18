package mst.shr.imdb.imdbproject.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mst.shr.imdb.imdbproject.models.dbModels.Movie;
import mst.shr.imdb.imdbproject.models.responseModels.ApiResponseModel;
import mst.shr.imdb.imdbproject.services.MovieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/queries")
@Tag(name = "Queries", description = "Endpoints to query the DB")
public class MovieController {

    private MovieServiceImpl movieService;

    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }


    // - GET localhost:8090/api/queries/genresBestSellingMovies
    // (Gets a genre name as a string and returns best-selling movie title on each year for that genre)
    @Operation(summary = "Gets a genre name as a string and returns best-selling movie title on each year for that genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information successfully retrieved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseModel.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseModel.class))) })
    @GetMapping(value = "/genresBestSellingMovies", produces = { "application/json" })
    public ResponseEntity<ApiResponseModel<HashMap<Integer, Movie>>> getGenresBestSellingMovies(@Parameter(description = "Genres name")  @RequestParam String genres) {

        HashMap<Integer, Movie> movieHashMap =  movieService.getGenresBestSellingMovies(genres);

        // initiating the response
        ApiResponseModel<HashMap<Integer, Movie>> apiResponseModel = new ApiResponseModel<>();
        apiResponseModel.setMessage("OK");

        if(movieHashMap.size()==0)
            apiResponseModel.setDescription("Query executed successfully, but found no records");
        else
            apiResponseModel.setDescription("Query executed successfully");

        apiResponseModel.setRecords(movieHashMap);

        // send response with 200 status code
        return new ResponseEntity(apiResponseModel, HttpStatus.OK);
    }

    // - GET localhost:8090/api/queries/moviesWithOneAlivePersonAsWriterAndDirector
    // (returns list of movies in which both writer and director are same person and alive)
    @Operation(summary = "returns list of movies in which both writer and director are same person and alive")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information successfully retrieved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseModel.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseModel.class))) })
    @GetMapping(value = "/moviesWithOneAlivePersonAsWriterAndDirector", produces = { "application/json" })
    public ResponseEntity<ApiResponseModel<List<Movie>>> getMoviesWithOneAlivePersonAsWriterAndDirector() {

        List<Movie> movieList =  movieService.getMoviesWithOneAlivePersonAsWriterAndDirector();

        // initiating the response
        ApiResponseModel<List<Movie>> apiResponseModel = new ApiResponseModel<>();
        apiResponseModel.setMessage("OK");

        if(movieList.size() == 0)
            apiResponseModel.setDescription("Query executed successfully, but found no records");
        else
            apiResponseModel.setDescription("Query executed successfully");

        apiResponseModel.setRecords(movieList);

        // send response with 200 status code
        return new ResponseEntity(apiResponseModel, HttpStatus.OK);
    }



    // - GET localhost:8090/api/queries/commonMoviesOfTwoActors
    // (Gets two person uniq id and returns movies that both of them played in)
    @Operation(summary = "Gets two person's uniq id and returns movies that both of them played in")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information successfully retrieved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseModel.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseModel.class))) })
    @GetMapping(value = "/commonMoviesOfTwoActors", produces = { "application/json" })
    public ResponseEntity<ApiResponseModel<List<Movie>>> getCommonMoviesOfTwoActors(@Parameter(description = "First actor uniq ID")  @RequestParam String actor1, @Parameter(description = "Second actor uniq ID")  @RequestParam String actor2) {

        List<Movie> movieList =  movieService.getCommonMoviesOfTwoActors(actor1, actor2);

        // initiating the response
        ApiResponseModel<List<Movie>> apiResponseModel = new ApiResponseModel<>();
        apiResponseModel.setMessage("OK");

        if(movieList.size() == 0)
            apiResponseModel.setDescription("Query executed successfully, but found no records");
        else
            apiResponseModel.setDescription("Query executed successfully");

        apiResponseModel.setRecords(movieList);

        // send response with 200 status code
        return new ResponseEntity(apiResponseModel, HttpStatus.OK);
    }

    }
