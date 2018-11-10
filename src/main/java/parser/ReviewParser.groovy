package parser

import model.Review

import java.util.stream.Collectors

class ReviewParser {
    List<Review> parse(String path) {
        File file = new File(getClass().getResource(path).toURI())

        def reviewStrings = file.text.split('(\r\n){3,}').toList()

        return reviewStrings.stream()
                .map { s ->
            def reviewData = s.split('(\r\n){1,}').toList()

            def review = new Review()
            review.movieName = reviewData.get(0)

            def userNameData = reviewData.get(1).split('\\s+').toList()
            review.userFirstName = userNameData.get(0)
            review.userLastName = userNameData.get(1)
            review.reviewText = reviewData.get(2)

            return review
        }
        .collect(Collectors.toList())
    }
}
