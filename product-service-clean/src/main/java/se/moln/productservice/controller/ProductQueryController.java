package se.moln.productservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.moln.productservice.dto.ProductResponse;
import se.moln.productservice.service.ProductQueryService;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductQueryController {

    private final ProductQueryService service;

    public ProductQueryController(ProductQueryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.listActive(page, size));
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<Page<ProductResponse>> listByCategory(
            @PathVariable UUID categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.listByCategory(categoryId, page, size));
    }
}
