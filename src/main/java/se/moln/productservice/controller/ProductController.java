package se.moln.productservice.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.moln.productservice.dto.PageResponse;
import se.moln.productservice.dto.ProductRequest;
import se.moln.productservice.dto.ProductResponse;
import se.moln.productservice.service.ProductService;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest req) {
        System.out.println("kontroller");
        System.out.println("hello");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir){
        System.out.println("Hämtar alla produkter med paginering från kontroller");

        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProductResponse> products = service.getAllProducts(pageable);

        PageResponse<ProductResponse> response = new PageResponse<>(products);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProductsWithoutPagination(){
        List<ProductResponse> products = service.getAllProductsWithoutPagination();
        return ResponseEntity.ok(products);
    }

  
    @Operation(
            summary = "Ladda upp produktbild",
            description = "Skicka som multipart/form-data med fältet 'file'. Bilden sparas lokalt och kopplas till produkten."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping(
            path = "/{id}/images",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductResponse> uploadImage(
            @Parameter(
                    name = "id",
                    description = "Produktens UUID",
                    example = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3"
            )
            @PathVariable UUID id,

            @Parameter(description = "Bildfil (jpg/png/webp)")
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(imageService.uploadImage(id, file));
    }



}
}
