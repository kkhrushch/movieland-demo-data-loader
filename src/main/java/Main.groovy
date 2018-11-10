import config.Config
import dao.AppUserDao
import dao.GenreDao
import dao.MovieDao
import dao.PosterDao
import model.AppUser
import model.Genre
import model.Movie
import model.Poster
import parser.AppUserParser
import parser.GenreParser
import parser.MovieParser
import parser.PosterParser

//Config config = new Config();

//List<Genre> genres = new GenreParser().parse("/genre.txt")
//new GenreDao().load(genres)

//List<AppUser> users = new AppUserParser().parse('/user.txt')
//new AppUserDao().load(users)

//List<Movie> movies = new MovieParser().parse('/movie.txt')
//new MovieDao().load(movies)

List<Poster> posters =  new PosterParser().parse('/poster.txt')
new PosterDao().load(posters)

println "done";