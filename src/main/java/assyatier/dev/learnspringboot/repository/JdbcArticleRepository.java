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

    List<Article> findAllByDeletedAtIsNullOrderByUpdatedAtDesc();

    Optional<Article> findByIdAndDeletedAtIsNull(Long id);

    @Query("SELECT COUNT(a) FROM Article a WHERE a.deletedAt IS NULL")
    Integer countAllArticles();
}

