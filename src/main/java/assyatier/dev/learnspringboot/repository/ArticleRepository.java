package assyatier.dev.learnspringboot.repository;

import assyatier.dev.learnspringboot.models.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    List<Article> findAll();
    Optional<Article> findByTitle(String title);
    Optional<Article> findByID(Integer id);

    void create(Article article);
    void update(Article article, Integer id);
    void delete(Integer id);
    int count();
}
