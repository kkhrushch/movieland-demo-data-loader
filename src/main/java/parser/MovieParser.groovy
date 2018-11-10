package parser

import model.Movie

class MovieParser {
    List<Movie> parse(String path){
        File movieFile = new File(getClass().getResource('/movie.txt').toURI())
        println movieFile.getAbsolutePath()

        def moviesString = movieFile.text

        def movies = moviesString.split("\r\n\r\n\r\n")

        println movies.size()

        movies.each{s ->
            s = s.replace("\r\n\r\n", "\r\n")
            String movieDetails = s.split("\r\n")
            println(movieDetails.size())
        }

    }
}
