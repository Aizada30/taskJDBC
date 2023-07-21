package kinoTeatr.service.serviceImpl;

import kinoTeatr.dao.MovieDao;
import kinoTeatr.dao.daoIMPL.MovieDaoImpl;
import kinoTeatr.model.Movie;
import kinoTeatr.service.MovieService;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    MovieDao movieDao = new MovieDaoImpl();
    @Override
    public String createMovie(String tableName, List<String> columns) {
        movieDao.createTable(tableName,columns);
        return "Successfully created table with name:"+tableName;
    }

    @Override
    public String saveMovie(Movie movie) {
        movieDao.save(movie);
        return "success saved";
    }

    @Override
    public Movie findMovieById(Long id) {
        return movieDao.findById(id);
    }
}
