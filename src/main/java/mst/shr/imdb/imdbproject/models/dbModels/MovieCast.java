package mst.shr.imdb.imdbproject.models.dbModels;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="movie_cast")
@Getter
@Setter
public class MovieCast {

    public MovieCast(String movieId, String personId, Role role) {
        this.movieId = movieId;
        this.personId = personId;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movieId;
    private String personId;

    @Enumerated(EnumType.ORDINAL)
    private Role role;
}
