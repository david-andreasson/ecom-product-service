package se.moln.productservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.moln.productservice.dto.ProductRequest;
import se.moln.productservice.dto.ProductResponse;
import se.moln.productservice.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest req){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }
}
