package se.moln.productservice.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.moln.productservice.dto.StockResponse;
import se.moln.productservice.model.Product;
import se.moln.productservice.repository.ProductRepository;

import java.util.UUID;

@Service
@Transactional
public class InventoryService {
    private final ProductRepository repo;

    public InventoryService(ProductRepository repo) { this.repo = repo; }

    public StockResponse reserve(UUID id, int qty) {
        if (qty <= 0) throw new IllegalArgumentException("qty must be > 0");
        Product p = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        p.reserveStock(qty);
        repo.save(p);
        return toResponse(p);
    }

    public StockResponse release(UUID id, int qty) {
        if (qty <= 0) throw new IllegalArgumentException("qty must be > 0");
        Product p = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        p.releaseStock(qty);
        repo.save(p);
        return toResponse(p);
    }

    public StockResponse status(UUID id) {
        Product p = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return toResponse(p);
    }

    private StockResponse toResponse(Product p) {
        String status = (p.isActive() && p.getStockQuantity() > 0) ? "InStock" : "OutOfStock";
        return new StockResponse(p.getId(), p.getStockQuantity(), status);
    }
}
