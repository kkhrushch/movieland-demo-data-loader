package dao

import config.Config
import model.Movie
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder

import java.sql.PreparedStatement

class MovieDao {
    private static final JdbcTemplate jdbcTemplate = Config.jdbcTemplate

    private static final String TRUNCATE_MOVIE_SQL = "TRUNCATE TABLE movie"
    private static final String TRUNCATE_MOVIE_GENRE_SQL = "TRUNCATE TABLE movie_genre"
    private static final String INSERT_MOVIE_SQL = "INSERT INTO movie(name, original_name, year, country, description, rating, price) VALUES(?, ?, ?, ?, ?, ?, ?)"
    private static final String INSERT_MOVIE_GENRE_SQL = "INSERT INTO movie_genre(movie_id, genre_id) VALUES(?, ?)"
    private static final String SELECT_GENRE_ID_SQL = "SELECT id FROM genre WHERE name = ?"

    void load(List<Movie> movies) {
        jdbcTemplate.update(TRUNCATE_MOVIE_SQL)
        jdbcTemplate.update(TRUNCATE_MOVIE_GENRE_SQL)

        movies.forEach { m -> loadMovie(m) }
    }

    private loadMovie(Movie movie) {
        KeyHolder keyHolder = new GeneratedKeyHolder()
        def returnColumnName = new String[1]
        returnColumnName[0] = "id"

        jdbcTemplate.update({ connection ->
            PreparedStatement statement = connection.prepareStatement(INSERT_MOVIE_SQL, returnColumnName)

            statement.setString(1, movie.name)
            statement.setString(2, movie.originalName)
            statement.setInt(3, movie.year)
            statement.setString(4, movie.country)
            statement.setString(5, movie.description)
            statement.setDouble(6, movie.rating)
            statement.setDouble(7, movie.price)

            return statement
        },
                keyHolder
        )

        def movieId = keyHolder.getKey().longValue()

        movie.genres.forEach { g ->
            def genreId = jdbcTemplate.queryForObject(SELECT_GENRE_ID_SQL, [g.name] as String[], long.class)
            jdbcTemplate.update(INSERT_MOVIE_GENRE_SQL, movieId, genreId)
        }
    }
}
