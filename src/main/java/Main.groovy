import config.Config
import dao.AppUserDao
import dao.GenreDao
import dao.MovieDao
import model.AppUser
import model.Genre
import model.Movie
import parser.AppUserParser
import parser.GenreParser
import parser.MovieParser

//Config config = new Config();

//List<Genre> genres = new GenreParser().parse("/genre.txt")
//new GenreDao().load(genres)

//List<AppUser> users = new AppUserParser().parse('/user.txt')
//new AppUserDao().load(users)

List<Movie> movies = new MovieParser().parse('/movie.txt')
new MovieDao().load(movies)

println "done";