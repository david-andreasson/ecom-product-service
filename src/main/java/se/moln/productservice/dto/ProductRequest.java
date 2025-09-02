package se.moln.productservice.dto;

import jakarta.validation.constraints.*;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record ProductRequest(
        @NotBlank String name,
        String description,
        @NotNull @Positive BigDecimal price,
        @NotBlank String currency,
        UUID categoryId,
        @Min(0) Integer stockQuantity,
        Map<String,String> attributes,
        List<String> imageUrls // ni kan börja med rena URL:er
) {}