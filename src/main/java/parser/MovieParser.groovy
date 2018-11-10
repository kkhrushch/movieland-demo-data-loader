package parser

import model.Genre
import model.Movie

import java.util.stream.Collectors

class MovieParser {
    List<Movie> parse(String path) {
        File file = new File(getClass().getResource(path).toURI())

        List<String> movieStrings = file.text.split("(\r\n){4}").toList()

        return movieStrings.stream()
                .map { s -> getMovieFromString(s) }
                .collect(Collectors.toList())
    }

    private Movie getMovieFromString(String movieString) {
        List<String> movieData = movieString.split('(\r\n){1,2}')
        Movie movie = new Movie()

        List<String> names = movieData.get(0).split('/')
        movie.name = names.get(0)
        movie.originalName = names.get(1)

        movie.year = movieData.get(1).toInteger()
        movie.country = movieData.get(2)

        movie.genres = movieData.get(3).split(',\\s*').toList().stream().map { s -> new Genre(s) }.collect(Collectors.toList())

        movie.description = movieData.get(4)

        movie.rating = movieData.get(5).split(':')[1].toDouble()
        movie.price = movieData.get(6).split(':')[1].toDouble()

        return movie;
    }
}
