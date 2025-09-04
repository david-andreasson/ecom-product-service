package se.moln.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import se.moln.productservice.dto.ProductResponse;
import se.moln.productservice.service.ProductReadService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductReadController.class)
class ProductReadControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockitoBean private ProductReadService readService;

    @Autowired private ObjectMapper objectMapper;

    @Test
    void getById_ShouldReturnProduct() throws Exception {
        UUID id = UUID.randomUUID();
        ProductResponse resp = new ProductResponse(
                id, "Test Product", "test-product", "Desc",
                BigDecimal.valueOf(99.99), "SEK", "Electronics", 10, true,
                Map.of("color","black"), List.of("http://example.com/image.jpg")
        );

        when(readService.getById(any(UUID.class))).thenReturn(resp);

        mockMvc.perform(get("/api/products/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void getById_NotFound_ShouldReturn404() throws Exception {
        UUID id = UUID.randomUUID();
        when(readService.getById(any(UUID.class)))
                .thenThrow(new EntityNotFoundException("Product not found: " + id));

        mockMvc.perform(get("/api/products/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"));
    }
}
