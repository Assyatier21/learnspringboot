package assyatier.dev.learnspringboot.utils;

import assyatier.dev.learnspringboot.models.Article;
import java.util.List;
import java.util.Set;

public class ArticleUtils {

    private ArticleUtils() {}

    public static List<Article> getArticles() {
        return List.of(
            new Article(1L, "First Article", "first-article", "<p>This is the first article</p>", "John Doe", null, null, null),
            new Article(2L, "Second Article", "second-article", "<p>This is the second article</p>", "John Doe", null, null, null),
            new Article(3L, "Third Article", "third-article", "<p>This is the third article</p>", "John Doe", null, null, null),
            new Article(4L, "Fourth Article", "fourth-article", "<p>This is the fourth article</p>", "John Doe", null, null, null),
            new Article(5L, "Fifth Article", "fifth-article", "<p>This is the fifth article</p>", "John Doe", null, null, null)
        );
    }

}
