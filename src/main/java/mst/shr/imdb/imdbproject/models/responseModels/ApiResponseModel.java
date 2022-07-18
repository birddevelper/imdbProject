package mst.shr.imdb.imdbproject.models.responseModels;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ApiResponseModel<T> {

    private String message;
    private String description;
    private T Records;
}
