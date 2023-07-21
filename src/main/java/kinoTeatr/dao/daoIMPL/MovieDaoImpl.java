package kinoTeatr.dao.daoIMPL;

import kinoTeatr.config.JdbcConfig;
import kinoTeatr.dao.MovieDao;
import kinoTeatr.model.Movie;

import java.sql.*;
import java.util.List;

public class MovieDaoImpl implements MovieDao {
    private final Connection connection = JdbcConfig.getConnection();

    @Override
    public void createTable(String tableName, List<String> columns) {
        StringBuilder stringBuilder = new StringBuilder(String.format("create table %s (", tableName));
        try {
            Statement statement = connection.createStatement();
            for (int i = 0; i < columns.size(); i++) {
                stringBuilder.append(columns.get(i));
                if (i < columns.size() - 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append(")");
            statement.executeUpdate(stringBuilder.toString());
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Movie movie) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("""
                insert into movies(title,genre,duration) values (?,?,?);
                             """);) {
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getGenre());
            preparedStatement.setInt(3, movie.getDuration());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Movie findById(Long id) {
        Movie movie = new Movie();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                                       select * from movies where id=?;
                    """);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!(resultSet.next())) {
                System.err.println("Movie with id: " + id + "not found!!!");
            }
                else{
                    movie.setId(resultSet.getLong("id"));
                    movie.setTitle(resultSet.getString("title"));
                    movie.setGenre(resultSet.getString("genre"));
                    movie.setDuration(resultSet.getInt("duration"));
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
