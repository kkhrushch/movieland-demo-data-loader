package config

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource

import javax.sql.DataSource

class Config {
    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost/movieland";
    private static final String USER = "postgres";
    private static final String PASS = "root";

    static final JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

//    Config(){
//        jdbcTemplate = new JdbcTemplate(getDataSource());
//    }

    static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        return dataSource;
    }
}
