package dao

import config.Config
import model.Poster
import org.springframework.jdbc.core.JdbcTemplate

class PosterDao {
    private static final JdbcTemplate jdbcTemplate = Config.jdbcTemplate
    private static final String UPDATE_POSTER_SQL = "UPDATE movie SET poster_url = ? WHERE name = ?"

    void load(List<Poster> posters) {
        posters.stream()
                .forEach { p ->
            jdbcTemplate.update(UPDATE_POSTER_SQL, p.posterUrl, p.movieName)
        }
    }
}
