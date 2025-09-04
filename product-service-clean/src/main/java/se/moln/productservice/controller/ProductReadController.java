package se.moln.productservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.moln.productservice.dto.ProductResponse;
import se.moln.productservice.service.ProductReadService;

import java.util.UUID;

/**
 * Read-only endpoint që plotëson listën & kërkimin ekzistues.
 * Shton GET /api/products/{id} pa prekur controller-in ekzistues.
 */
@RestController
@RequestMapping("/api/products")
public class ProductReadController {

    private final ProductReadService readService;

    public ProductReadController(ProductReadService readService) {
        this.readService = readService;
    }

    @Operation(
            summary = "Hämta produkt via ID",
            description = "Returnerar en produkt som ProductResponse. 404 om produkten saknas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(
            @Parameter(description = "Produktens UUID", example = "b7f8f3f9-3e8c-4612-9d8a-1d4c9f77a001")
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(readService.getById(id));
    }
}
