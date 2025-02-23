package assyatier.dev.learnspringboot.repository;

import assyatier.dev.learnspringboot.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JdbcArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByDeletedAtIsNull();

    List<Article> findAllByTitleAndDeletedAtIsNull(String title);

    List<Article> findAllByDeletedAtIsNullAndOrderByUpdatedAtDesc();

    List<Article> findByTitleAndDeletedAtIsNull();

    Optional<Article> findByIdAndDeletedAtIsNull(Integer id);

    @Query("SELECT COUNT(*) FROM articles WHERE deleted_at IS NULL")
    Integer countAllArticles();
}
