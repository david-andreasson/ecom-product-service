package se.moln.productservice.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record ProductResponse(
        UUID id, String name, String slug, String description,
        BigDecimal price, String currency, String categoryName,
        int stockQuantity, boolean active,
        Map<String,String> attributes, List<String> images
) {}