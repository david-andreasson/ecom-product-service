package se.moln.productservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    public ProductController(ProductService service) {
        this.service = service;
    }


    @Operation(
            summary = "Skapa en produkt",
            description = "Skapar en ny produkt. Antingen ange categoryId eller categoryName (eller lämna båda tomma för 'Uncategorized').",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Skapad"),
                    @ApiResponse(responseCode = "400", description = "Valideringsfel"),
                    @ApiResponse(responseCode = "409", description = "Konflikt (t.ex. slug eller attribut-nyckel i konflikt)")
            }
    )
    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }
}
