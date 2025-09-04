package se.moln.productservice.exeption;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class DataIntegrityAdvice {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handle(DataIntegrityViolationException ex){
        return ResponseEntity.status(409).body(Map.of(
                "error","conflict",
                "message","Unique constraint violation (likely slug already exists)"
        ));
    }
}
