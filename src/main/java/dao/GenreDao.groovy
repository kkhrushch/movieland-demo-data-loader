package dao

import config.Config
import model.Genre
import org.springframework.jdbc.core.JdbcTemplate

class GenreDao {
    JdbcTemplate jdbcTemplate = Config.jdbcTemplate;

    private static final TRUNCATE_GENRE_TABLE_SQL = "TRUNCATE TABLE genre";
    private static final INSERT_GENRE_SQL = "INSERT INTO genre(name) VALUES(?)";

    void load(List<Genre> genres){
        jdbcTemplate.update(TRUNCATE_GENRE_TABLE_SQL)

        genres.forEach{g -> jdbcTemplate.update(INSERT_GENRE_SQL, g.name)}

    }
}
