package assyatier.dev.learnspringboot.service;

import assyatier.dev.learnspringboot.dto.ArticleRequest;
import assyatier.dev.learnspringboot.repository.JdbcArticleRepository;
import org.springframework.stereotype.Service;
import assyatier.dev.learnspringboot.models.Article;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final JdbcArticleRepository articleRepository;

    public ArticleService(JdbcArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticleByTitle(String title) {
        return articleRepository.findByTitle(title);
    }

    public Optional<Article> getArticleById(Integer id) {
        return articleRepository.findByID(id);
    }

    public Article createArticle(ArticleRequest article) {
        Article newArticle = new Article(null, article.getTitle(), article.getSlug(), article.getHtmlContent(), article.getAuthor(), null, null, null);

        articleRepository.create(newArticle);
        return newArticle;
    }

    public Optional<Article> updateArticle(Article article, Integer id) {
        articleRepository.update(article, id);
        return Optional.of(article);
    }

    public boolean deleteArticle(Integer id) {
        articleRepository.delete(id);
        return true;
    }

    public int getArticleCount() {
        return articleRepository.count();
    }
}
