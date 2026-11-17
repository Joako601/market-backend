package mx.edu.tecdesotware.Market_Backend.web.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Ej.: clientId, productId o categoryId que no existen (violación de llave foránea).
    // Sin este handler la respuesta sería un 500.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrity(DataIntegrityViolationException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "Invalid data: a referenced record does not exist or a constraint was violated");
    }
}
