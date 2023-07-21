package kinoTeatr.service;

import kinoTeatr.model.Movie;

import java.util.List;

public interface MovieService {
    String createMovie(String tableName, List<String> columns);

    //save
    String saveMovie(Movie movie);

    Movie findMovieById(Long id);
}
