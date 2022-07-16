package mst.shr.imdb.imdbproject.services;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import mst.shr.imdb.imdbproject.models.dbModels.*;
import mst.shr.imdb.imdbproject.repositories.MovieCastRepository;
import mst.shr.imdb.imdbproject.repositories.MovieGenresRepository;
import mst.shr.imdb.imdbproject.repositories.MovieRepository;
import mst.shr.imdb.imdbproject.repositories.PersonRepository;
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

    private PersonRepository personRepository;

    @Autowired
    public ImportServiceImpl(MovieRepository movieRepository, MovieCastRepository movieCastRepository,
                             MovieGenresRepository movieGenresRepository, PersonRepository personRepository) {
        this.movieRepository = movieRepository;
        this.movieCastRepository = movieCastRepository;
        this.movieGenresRepository = movieGenresRepository;
        this.personRepository = personRepository;
    }

    /**
     * This method reads uploaded file content and calls appropriate method based on content to import data to DB.
     *
     * @param uploadedFile   uploaded file
     */
    @Override
    public void importDataset(MultipartFile uploadedFile) throws NumberFormatException, IOException, NoSuchAlgorithmException, InvalidFormatException {
        LineIterator iterator = null;

        File file = FileUtilities.saveUploadedFile(uploadedFile, "c:\\uploads", true);
        iterator = FileUtils.lineIterator(file, "UTF-8");

        if (iterator.hasNext()) {
            String line = iterator.nextLine();
            String[] lineData = line.split("\t");

            if (lineData.length>=3) {
                // Here the content of the uploaded file is checked to choose appropriate import method
                // title.basic file
                if (lineData[0].equals("tconst") && lineData[2].equals("primaryTitle") &&
                        lineData[5].equals("startYear") && lineData[8].equals("genres")){
                        this.importMovieDataset(file);
                }
                // title.crew file
                else if (lineData[0].equals("tconst") && lineData[1].equals("directors") && lineData[2].equals("writers")){
                    this.importCrewDataset(file);
                }
                // title.principal file
                else if (lineData[0].equals("tconst") && lineData[2].equals("nconst") && lineData[3].equals("category")){
                    this.importPrincipalDataset(file);
                }
                // name.basic file
                else if(lineData[0].equals("nconst") && lineData[1].equals("primaryName") && lineData[3].equals("deathYear")){
                    this.importPersonsDataset(file);
                }
            }
            else {
                throw new InvalidFormatException();
            }

        }
    }

    /**
     * This method reads data from given title.basic (movie's basic info) tsv file and imports it into database
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
     * This method reads data from given title.crew (director and writer of the movies) tsv file and imports it into database
     *
     * @param datasetFile  Movie's crew dataset file
     */

    private void importCrewDataset(File datasetFile) throws NumberFormatException, IOException {

        LineIterator iterator = null;
        try {

            ArrayList<MovieCast> movieCastList = new ArrayList<>();
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
                        movieCastList.add(new MovieCast(movieId, directorId, Role.DIRECTOR));
                }

                if (!writersField.equals("\\N")) {
                    String[] writersList = writersField.split(",");
                    for (String writerId : writersList)
                        movieCastList.add(new MovieCast(movieId, writerId, Role.WRITER));
                }
            }

            movieCastRepository.saveAll(movieCastList);
        }
        finally {
            if(iterator!=null)
                LineIterator.closeQuietly(iterator);
        }
    }

    /**
     * This method reads data from given title.principal (people involved in making movie) tsv file and imports it into database
     *
     * @param datasetFile  Movie's principal dataset file
     */

    private void importPrincipalDataset(File datasetFile) throws NumberFormatException, IOException {

        LineIterator iterator = null;
        try {

            ArrayList<MovieCast> movieCastList = new ArrayList<>();
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
                    movieCastList.add(new MovieCast(movieId, actor, Role.ACTOR));
                }

            }

            movieCastRepository.saveAll(movieCastList);
        }
        finally {
            if(iterator!=null)
                LineIterator.closeQuietly(iterator);
        }
    }


    /**
     * This method reads data from given name.basic (person's basic info) tsv file and imports it into database
     *
     * @param datasetFile  person's crew dataset file
     */

    private void importPersonsDataset(File datasetFile) throws NumberFormatException, IOException {

        LineIterator iterator = null;
        try {

            ArrayList<Person> personList = new ArrayList<>();
            iterator = FileUtils.lineIterator(datasetFile, "UTF-8");
            boolean firstLine = true;
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                // read data from the required fields
                String[] lineData = line.split("\t");
                String personId = lineData[0]; // Person's uniq Id
                String personName = lineData[1]; // person's name
                boolean isAlive = lineData[3].equals("\\N"); // If death year field is empty, he/she is alive

                personList.add(new Person(personId, personName, isAlive));

            }

            personRepository.saveAll(personList);
        }
        finally {
            if(iterator!=null)
                LineIterator.closeQuietly(iterator);
        }
    }
}
