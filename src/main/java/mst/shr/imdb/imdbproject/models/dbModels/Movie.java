package mst.shr.imdb.imdbproject.models.dbModels;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="movie")
@Getter
@Setter
public class Movie {

    @Id
    private String id;
    private String title;
    private int releaseYear;
    private float averageRate;
    private int votes;

}