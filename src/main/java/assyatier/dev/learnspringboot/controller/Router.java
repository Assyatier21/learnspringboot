package assyatier.dev.learnspringboot.controller;

import assyatier.dev.learnspringboot.dto.ApiResponse;
import assyatier.dev.learnspringboot.dto.ArticleRequest;
import assyatier.dev.learnspringboot.models.Article;
import assyatier.dev.learnspringboot.service.ArticleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/articles")
class ArticleController {
    private final ArticleService articleService;

    // Get all articles
    @GetMapping
    public ResponseEntity<ApiResponse<List<Article>>> getAllArticles(
            @RequestParam(required = false) String title){
        List<Article> articleList = articleService.getAllArticles(title);
        ApiResponse<List<Article>> response = new ApiResponse<>(true, "Successfully fetched list of articles", articleList);
        return ResponseEntity.ok(response);  // 200 OK
    }

    // Get article by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Article>> getArticleById(@PathVariable Integer id) {
        Optional<Article> article = articleService.getArticleById(id);
        ApiResponse<Article> response = article.map(a -> new ApiResponse<>(true, "Article found", a))
                .orElseGet(() -> new ApiResponse<>(false, "Article not found", null));
        return ResponseEntity.status(article.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }

    // Create a new article
    @PostMapping
    public ResponseEntity<ApiResponse<Article>> createArticle(@Valid @RequestBody ArticleRequest article) {
        Article createdArticle = articleService.createArticle(article);
        ApiResponse<Article> response = new ApiResponse<>(true, "Article created successfully", createdArticle);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update an existing article
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Article>> updateArticle(@PathVariable Integer id, @Valid @RequestBody Article article) throws Exception {
        Optional<Article> updatedArticle = articleService.updateArticle(article, id);
        ApiResponse<Article> response = updatedArticle.map(a -> new ApiResponse<>(true, "Article updated successfully", a))
                .orElseGet(() -> new ApiResponse<>(false, "Article not found", null));
        return ResponseEntity.status(updatedArticle.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }

    // Delete an article
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteArticle(@PathVariable Integer id) {
        boolean isDeleted = articleService.deleteArticle(id);
        ApiResponse<Void> response = isDeleted ? new ApiResponse<>(true, "Article deleted successfully", null)
                : new ApiResponse<>(false, "Article not found", null);
        return ResponseEntity.status(isDeleted ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }

    // Get the total count of articles
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> getArticleCount() {
        int count = articleService.getArticleCount();
        ApiResponse<Integer> response = new ApiResponse<>(true, "Article count fetched successfully", count);
        return ResponseEntity.ok(response);  // 200 OK
    }
}
