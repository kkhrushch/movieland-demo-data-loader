package parser

import model.Poster

import java.util.stream.Collectors

class PosterParser {
    List<Poster> parse(String path){
        File file = new File(getClass().getResource(path).toURI())

        List<String> posterStrings = file.text.split("(\r\n)+").toList()

        return posterStrings.stream().map{s ->
            def posterData = s =~ /^(.*)\s(https?:\/\/.*)/
            def movieName = posterData[0][1]
            def posterUrl = posterData[0][2]
            return new Poster(movieName, posterUrl)
        }
        .collect(Collectors.toList())
    }
}
