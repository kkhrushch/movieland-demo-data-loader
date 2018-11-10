import dao.*
import model.*
import parser.*

List<Genre> genres = new GenreParser().parse("/genre.txt")
new GenreDao().load(genres)

List<AppUser> users = new AppUserParser().parse('/user.txt')
new AppUserDao().load(users)

List<Movie> movies = new MovieParser().parse('/movie.txt')
new MovieDao().load(movies)

List<Poster> posters = new PosterParser().parse('/poster.txt')
new PosterDao().load(posters)

List<Review> reviews = new ReviewParser().parse('/review.txt')
new ReviewDao().load(reviews)
