package se.moln.productservice.dto;
import java.util.UUID;

public record StockResponse(UUID id, int stockQuantity, String status) {

}

