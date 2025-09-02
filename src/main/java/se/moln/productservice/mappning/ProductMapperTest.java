package se.moln.productservice.mappning;
import org.junit.jupiter.api.Test;
import se.moln.productservice.dto.ProductRequest;
import se.moln.productservice.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper mapper = new ProductMapper();

    @Test
    void toEntity_setsFields_andSlugifiedName() {
        ProductRequest req = new ProductRequest(
                "MacBook Pro 16 VÅR 2025!!",
                "desc",
                new BigDecimal("19999.90"),
                "SEK",
                null,
                3,
                Map.of("ram", "32GB"),
                List.of("https://img")
        );

        Product p = mapper.toEntity(req, null);

        assertEquals("MacBook Pro 16 VÅR 2025!!", p.getName());
        assertEquals(new BigDecimal("19999.90"), p.getPrice());
        assertEquals("SEK", p.getCurrency());
        assertEquals(3, p.getStockQuantity());
        assertEquals("32GB", p.getAttributes().get("ram"));
        assertEquals(1, p.getImages().size());
        assertEquals("macbook-pro-16-v-r-2025", p.getSlug());
    }
}
