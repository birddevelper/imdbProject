package mst.shr.imdb.imdbproject.models.dbModels;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="movie_genres")
@Getter
@Setter
public class MovieGenres {

    public MovieGenres(String movieId, String genres) {
        this.movieId = movieId;
        this.genres = genres;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movieId;
    private String genres;

}
