package se.moln.productservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import se.moln.productservice.model.Product;
import se.moln.productservice.repository.ProductRepository;

import java.math.BigDecimal;

@Component
@Profile("!test")
public class DataSeeder implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);
    private final ProductRepository products;

    public DataSeeder(ProductRepository products) {
        this.products = products;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            seedAiHoroscope();
        } catch (Exception e) {
            log.warn("Data seeder failed: {}", e.getMessage());
        }
    }

    private void seedAiHoroscope() {
        final String name = "AI Horoscope (PDF)";
        if (products.existsByNameIgnoreCase(name)) {
            log.info("Seed: product '{}' already exists, skipping", name);
            // Migrera gamla absoluta URL:er (8085/8080) till relativa paths för port-oberoende leverans
            products.findAll().forEach(p -> {
                if (p.getImages() != null && !p.getImages().isEmpty()) {
                    boolean changed = false;
                    var updated = new java.util.ArrayList<se.moln.productservice.model.ProductImage>(p.getImages().size());
                    for (var img : p.getImages()) {
                        String url = img.getUrl();
                        if (url != null) {
                            String newUrl = url
                                    .replace("http://localhost:8085/", "/")
                                    .replace("http://localhost:8080/", "/");
                            if (!newUrl.equals(url)) changed = true;
                            updated.add(new se.moln.productservice.model.ProductImage(newUrl, img.getFileName(), img.getContentType(), img.getSizeBytes()));
                        } else { updated.add(img); }
                    }
                    if (changed) {
                        p.setImages(updated);
                        products.save(p);
                        log.info("Migrated image URLs to relative paths for product '{}'", p.getName());
                    }
                }
            });
            return;
        }
        Product p = new Product();
        p.setName(name);
        p.setSlug("ai-horoscope-pdf");
        p.setDescription("Personalized AI horoscope delivered as downloadable PDF.");
        p.setPrice(new BigDecimal("19.90"));
        p.setCurrency("SEK");
        p.setStockQuantity(100000);
        p.setActive(true);
        // Spara relativ URL så gateway/host spelar ingen roll
        p.addImage("/horoscope-placeholder.svg", "horoscope-placeholder.svg", "image/svg+xml", 0L);
        products.save(p);
        log.info("Seed: created '{}' with price {} {}", p.getName(), p.getPrice(), p.getCurrency());
    }
}

