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
@Entity(name="person")
@Getter
@Setter
public class Person {

    @Id
    private String id;
    private String name;
    private boolean alive;
}
