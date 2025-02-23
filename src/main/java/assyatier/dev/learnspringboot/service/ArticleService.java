package assyatier.dev.learnspringboot.service;

import assyatier.dev.learnspringboot.dto.ArticleRequest;
import assyatier.dev.learnspringboot.repository.JdbcArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import assyatier.dev.learnspringboot.models.Article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {
    private final JdbcArticleRepository articleRepository;

    public List<Article> getAllArticles(String title) {
        if(title != null) {
            return articleRepository.findAllByTitleAndDeletedAtIsNull(title);
        }

        return articleRepository.findAllByDeletedAtIsNullOrderByUpdatedAtDesc();
    }

    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findByIdAndDeletedAtIsNull(id);
    }

    public Article createArticle(ArticleRequest article) {
        Article newArticle = new Article(
                null,
                article.getTitle(),
                article.getSlug(),
                article.getHtmlContent(),
                article.getAuthor(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null);

        articleRepository.save(newArticle);
        return newArticle;
    }

    public Optional<Article> updateArticle(Article article, Long id) throws Exception {
        Article existingArticle = articleRepository.findByIdAndDeletedAtIsNull(id).
                orElseThrow(() -> new Exception("Article not found"));

        existingArticle.setTitle(article.getTitle());
        existingArticle.setSlug(article.getSlug());
        existingArticle.setHtmlContent(article.getHtmlContent());
        existingArticle.setAuthor(article.getAuthor());
        existingArticle.setUpdatedAt(LocalDateTime.now());

        articleRepository.save(existingArticle);
        return Optional.of(existingArticle);
    }

    public boolean deleteArticle(Long id) {
        Article existingArticle = articleRepository.findByIdAndDeletedAtIsNull(id).
                orElseThrow(() -> new RuntimeException("Article not found"));

        existingArticle.setDeletedAt(LocalDateTime.now());
        articleRepository.delete(existingArticle);
        return true;
    }

    public int getArticleCount() {
        return articleRepository.countAllArticles();
    }
}
