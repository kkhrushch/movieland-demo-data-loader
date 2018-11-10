package dao

import config.Config
import model.AppUser
import org.springframework.jdbc.core.JdbcTemplate

class AppUserDao {
    JdbcTemplate jdbcTemplate = Config.jdbcTemplate;

    private static final TRUNCATE_APP_USER_TABLE_SQL = "TRUNCATE TABLE app_user";
    private static final INSERT_APP_USER_SQL = "INSERT INTO app_user(first_name, last_name, role, email, password) VALUES(?, ?, ?, ?, ?)";

    void load(List<AppUser> appUsers) {
        jdbcTemplate.update(TRUNCATE_APP_USER_TABLE_SQL)

        appUsers.forEach { u ->
            jdbcTemplate.update(INSERT_APP_USER_SQL,
                    u.firstName,
                    u.lastName,
                    'USER',
                    u.email,
                    u.password
            )
        }

    }

}
