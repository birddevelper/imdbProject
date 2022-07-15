package mst.shr.imdb.imdbproject.services;

import mst.shr.imdb.imdbproject.models.dbModels.Movie;
import mst.shr.imdb.imdbproject.models.dbModels.MovieCast;
import mst.shr.imdb.imdbproject.models.dbModels.MovieGenres;
import mst.shr.imdb.imdbproject.models.dbModels.Role;
import mst.shr.imdb.imdbproject.repositories.MovieCastRepository;
import mst.shr.imdb.imdbproject.repositories.MovieGenresRepository;
import mst.shr.imdb.imdbproject.repositories.MovieRepository;
import mst.shr.imdb.imdbproject.utilities.FileUtilities;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

@Service
public class ImportServiceImpl implements ImportService {

    private MovieRepository movieRepository;

    private MovieCastRepository movieCastRepository;

    private MovieGenresRepository movieGenresRepository;

    @Autowired
    public ImportServiceImpl(MovieRepository movieRepository, MovieCastRepository movieCastRepository, MovieGenresRepository movieGenresRepository) {
        this.movieRepository = movieRepository;
        this.movieCastRepository = movieCastRepository;
        this.movieGenresRepository = movieGenresRepository;
    }

    /**
     * This method reads uploaded file content and calls appropriate method based on content to import data to DB.
     *
     * @param uploadedFile   uploaded file
     */
    @Override
    public void importDataset(MultipartFile uploadedFile) throws NumberFormatException, IOException, NoSuchAlgorithmException {
        LineIterator iterator = null;

        File file = FileUtilities.saveUploadedFile(uploadedFile, "c:\\uploads", true);
        iterator = FileUtils.lineIterator(file, "UTF-8");

        if (iterator.hasNext()) {
            String line = iterator.nextLine();
            String[] lineData = line.split("\t");

            // Here the content of the uploaded file is checked to choose appropriate import method
            // title.movie
            if (lineData[0].equals("tconst") && lineData[2].equals("primaryTitle") &&
                    lineData[5].equals("startYear") && lineData[8].equals("genres")){
                    this.importMovieDataset(file);
            }
            else if (lineData[0].equals("tconst") && lineData[1].equals("directors") && lineData[2].equals("writers")){
                this.importCrewDataset(file);
            }
            else if (lineData[0].equals("tconst") && lineData[2].equals("nconst") && lineData[3].equals("category")){
                this.importPrincipalDataset(file);
            }

        }
    }

    /**
     * This method reads data from given title.basic tsv file and imports it into database
     *
     * @param datasetFile   title's basic dataset file
     */

    private void importMovieDataset(File datasetFile) throws NumberFormatException, IOException {

        LineIterator iterator = null;
        try {

            ArrayList<Movie> moviesList = new ArrayList<>();
            ArrayList<MovieGenres> movieGenresList = new ArrayList<>();
            iterator = FileUtils.lineIterator(datasetFile, "UTF-8");
            boolean firstLine = true;
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] lineData = line.split("\t");
                String movieId = lineData[0];
                Movie movie = new Movie(
                        movieId, // uniq id of the movie
                        lineData[2], // primary title of the movie
                        parseInt(lineData[5]), // release year of the movie
                        0,      // rate of the movie
                        0      // number of votes
                );
                moviesList.add(movie);

                String[] genresArray = lineData[8].split(",");
                for (String genres : genresArray)
                    movieGenresList.add(new MovieGenres(movieId,genres));


            }

            movieRepository.saveAll(moviesList);
            movieGenresRepository.saveAll(movieGenresList);
        }
        finally {
            if(iterator!=null)
                LineIterator.closeQuietly(iterator);
        }
    }



    /**
     * This method reads data from given title.crew tsv file and imports it into database
     *
     * @param datasetFile  Movie's crew dataset file
     */

    private void importCrewDataset(File datasetFile) throws NumberFormatException, IOException {

        LineIterator iterator = null;
        try {

            ArrayList<MovieCast> movieCasts = new ArrayList<>();
            iterator = FileUtils.lineIterator(datasetFile, "UTF-8");
            boolean firstLine = true;
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] lineData = line.split("\t");
                String movieId = lineData[0];
                String directorsField = lineData[1];
                String writersField = lineData[2];

                if(!directorsField.equals("\\N")) {
                    String[] directorsList = directorsField.split(",");
                    for (String directorId : directorsList)
                        movieCasts.add(new MovieCast(movieId, directorId, Role.DIRECTOR));
                }

                if (!writersField.equals("\\N")) {
                    String[] writersList = writersField.split(",");
                    for (String writerId : writersList)
                        movieCasts.add(new MovieCast(movieId, writerId, Role.WRITER));
                }
            }

            movieCastRepository.saveAll(movieCasts);
        }
        finally {
            if(iterator!=null)
                LineIterator.closeQuietly(iterator);
        }
    }

    /**
     * This method reads data from given title.crew tsv file and imports it into database
     *
     * @param datasetFile  Movie's crew dataset file
     */

    private void importPrincipalDataset(File datasetFile) throws NumberFormatException, IOException {

        LineIterator iterator = null;
        try {

            ArrayList<MovieCast> movieCasts = new ArrayList<>();
            iterator = FileUtils.lineIterator(datasetFile, "UTF-8");
            boolean firstLine = true;
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] lineData = line.split("\t");
                String movieId = lineData[0];
                String category = lineData[3];
                if (category.equals("actor") || category.equals("actress") ) {
                    String actor = lineData[2];
                    movieCasts.add(new MovieCast(movieId, actor, Role.ACTOR));
                }

            }

            movieCastRepository.saveAll(movieCasts);
        }
        finally {
            if(iterator!=null)
                LineIterator.closeQuietly(iterator);
        }
    }
}
