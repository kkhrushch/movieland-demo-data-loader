package dao

import config.Config
import model.Review
import org.springframework.jdbc.core.JdbcTemplate

class ReviewDao {
    private static final JdbcTemplate jdbcTemplate = Config.jdbcTemplate

    private static final String TRUNCATE_MOVIE_REVIEW_SQL = "TRUNCATE TABLE movie_review"
    private static final String INSERT_REVIEW_SQL = "INSERT INTO movie_review(movie_id, app_user_id, text) VALUES(?, ?, ?)"
    private static final String SELECT_MOVIE_ID_SQL = "SELECT id FROM movie WHERE name = ?"
    private static final String SELECT_USER_ID_SQL = "SELECT id FROM app_user WHERE first_name = ? AND last_name = ?"

    void load(List<Review> reviews) {
        jdbcTemplate.update(TRUNCATE_MOVIE_REVIEW_SQL)

        reviews.stream()
                .forEach { r ->
            def movieId = jdbcTemplate.queryForObject(SELECT_MOVIE_ID_SQL, [r.movieName] as String[], long.class)
            def userId = jdbcTemplate.queryForObject(SELECT_USER_ID_SQL, [r.userFirstName, r.userLastName] as String[], long.class)
            jdbcTemplate.update(INSERT_REVIEW_SQL, movieId, userId, r.reviewText)
        }
    }
}
