package assyatier.dev.learnspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseError {
    private boolean success;
    private String message;
}
