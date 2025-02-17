package assyatier.dev.learnspringboot.repository;

import assyatier.dev.learnspringboot.models.Article;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.time.Instant;

@Repository
public class JdbcArticleRepository implements ArticleRepository{
    private final JdbcClient jdbcClient;

    public JdbcArticleRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Article> findAll() {
        return jdbcClient.sql("SELECT * FROM articles WHERE " +
                        "deleted_at IS NULL " +
                        "ORDER BY updated_at DESC")
                .query(Article.class)
                .list();
    }

    public Optional<Article> findByTitle(String title) {
        return jdbcClient.sql("SELECT * FROM articles WHERE " +
                        "title ILIKE %:title% " +
                        "AND deleted_at IS NULL")
                .param("title", title)
                .query(Article.class)
                .optional();
    }

    public Optional<Article> findByID(Integer id) {
        return jdbcClient.sql("SELECT * FROM articles WHERE id = :id AND deleted_at IS NULL")
                .param("id", id)
                .query(Article.class)
                .optional();
    }

    public void create(Article article) {
        var updated = jdbcClient.sql("INSERT INTO articles(title, slug, html_content, author, created_at, updated_at) VALUES(?,?,?,?,?,?)")
                .params(List.of(article.getTitle(), article.getSlug(), article.getHtmlContent(), article.getAuthor(), LocalDateTime.now(), LocalDateTime.now()))
                .update();
    }

    public void update(Article article, Integer id) {
        var updated = jdbcClient.sql("UPDATE articles SET title = ?, slug = ?, html_content = ?, author = ?, created_at = ?, updated_at = ? WHERE id = ?")
                .params(List.of(article.getTitle(), article.getSlug(), article.getHtmlContent(), article.getAuthor(), article.getCreatedAt(), article.getUpdatedAt(), id))
                .update();
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("UPDATE articles SET deleted_at = NOW() WHERE id = :id")
                .param("id", id)
                .update();
    }

    public int count() {
        return jdbcClient.sql("SELECT * FROM articles").query().listOfRows().size();
    }
}
