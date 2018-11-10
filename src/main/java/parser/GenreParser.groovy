package parser

import model.Genre

import java.util.stream.Collectors

class GenreParser {
    List<Genre> parse(String path) {
        File file = new File(getClass().getResource(path).toURI())

        return file.text.readLines().stream()
                .filter { l -> !l.isEmpty() }
                .map { l -> new Genre(l) }
                .collect(Collectors.toList());
    }
}
