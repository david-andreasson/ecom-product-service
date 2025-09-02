package se.moln.productservice.controller;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.moln.productservice.dto.StockResponse;
import se.moln.productservice.service.InventoryService;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) { this.service = service; }

    @PatchMapping("/{id}/reserve")
    public ResponseEntity<StockResponse> reserve(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "1") @Min(1) int qty) {
        return ResponseEntity.ok(service.reserve(id, qty));
    }

    @PatchMapping("/{id}/release")
    public ResponseEntity<StockResponse> release(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "1") @Min(1) int qty) {
        return ResponseEntity.ok(service.release(id, qty));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<StockResponse> status(@PathVariable UUID id) {
        return ResponseEntity.ok(service.status(id));
    }
}
