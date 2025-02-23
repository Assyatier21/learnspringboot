package assyatier.dev.learnspringboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleRequest {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Slug is required")
    private String slug;

    @NotBlank(message = "HTML Content is required")
    @JsonProperty("html_content")
    private String htmlContent;

    @NotBlank(message = "Author is required")
    private String author;
}
