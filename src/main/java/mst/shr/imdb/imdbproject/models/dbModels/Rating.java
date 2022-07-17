package mst.shr.imdb.imdbproject.models.dbModels;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="rating")
@Getter
@Setter
public class Rating {

    @Id
    private String movieId;
    private float averageRate;
    private int votes;
}
