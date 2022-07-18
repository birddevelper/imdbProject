**Imdb database Services** is a sample tiny application that performs operations via Restful Webservices.

It exposes 5 endpoint as following list :

**imdb Service :**

- GET localhost:8090/api/queries/genresBestSellingMovies (Gets a genre name as a string and returns best-selling movie title on each year for that genre)
- GET localhost:8090/api/queries/moviesWithOneAlivePersonAsWriterAndDirector (returns list of movies in which both writer and director are same person and alive)
- GET localhost:8090/api/queries/commonMoviesOfTwoActors (Gets two person's uniq id and returns movies that both of them played in)
- POST localhost:8090/api/db/import (open a new account, and returns the account number)
- GET localhost:8090/actuator/metrics/http.server.requests?tag=uri:endpoint_address {example : api/db/import} (endpoint metrics such as request count)



## How to setup
It is a docker based application. Running below command in project's directory builds single running container:

```bash
docker-compose up
```
**Note** : It may take time, because it needs to download all dependencies of the project.




## How to use
This application follows OpenAPI specification in API documentation. Thanks to SwaggerUI, you can see endpoints documentation in a graphical user interface and try their functionality and see the response. After running the containers, you can access the application links as :


- SwaggerUI : http://localhost:8090/swagger-ui.html


To Test the endpoints please go to *http://localhost:8080/swagger-ui.html*. It help you to read endpoints documentation and give you an UI to try them out.


**You can find miniDataset of imdb in project root directory**