package se.moln.productservice.exeption;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class IllegalArgumentAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handle(IllegalArgumentException ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : "Invalid argument";
        HttpStatus status = msg.toLowerCase().contains("category not found")
                ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(Map.of(
                "error", status == HttpStatus.NOT_FOUND ? "not_found" : "bad_request",
                "message", msg
        ));
    }
}
