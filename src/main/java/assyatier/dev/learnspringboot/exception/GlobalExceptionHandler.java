package assyatier.dev.learnspringboot.exception;

import assyatier.dev.learnspringboot.dto.ApiResponse;
import assyatier.dev.learnspringboot.dto.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorsNew[] = new String[ex.getBindingResult().getFieldErrors().size()];

        for (int i = 0; i < ex.getBindingResult().getFieldErrors().size(); i++) {
            errorsNew[i] = ex.getBindingResult().getFieldErrors().get(i).getDefaultMessage();
        }

        ApiResponseError response = new ApiResponseError(false, "Validation failed, err: " + Arrays.toString(errorsNew));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
