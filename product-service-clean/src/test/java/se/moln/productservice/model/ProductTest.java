package se.moln.productservice.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void reserveStock_happyPath_decreasesQuantity() {
        Product p = new Product();
        p.setStockQuantity(10);

        p.reserveStock(3);

        assertEquals(7, p.getStockQuantity());
    }

    @Test
    void reserveStock_zeroOrNegativeQty_throwsIllegalArgument() {
        Product p = new Product();
        p.setStockQuantity(10);

        assertThrows(IllegalArgumentException.class, () -> p.reserveStock(0));
        assertThrows(IllegalArgumentException.class, () -> p.reserveStock(-2));
    }

    @Test
    void reserveStock_insufficient_throwsIllegalState() {
        Product p = new Product();
        p.setStockQuantity(2);

        assertThrows(IllegalStateException.class, () -> p.reserveStock(3));
    }

    @Test
    void releaseStock_happyPath_increasesQuantity() {
        Product p = new Product();
        p.setStockQuantity(5);

        p.releaseStock(2);

        assertEquals(7, p.getStockQuantity());
    }

    @Test
    void releaseStock_zeroOrNegativeQty_throwsIllegalArgument() {
        Product p = new Product();
        p.setStockQuantity(5);

        assertThrows(IllegalArgumentException.class, () -> p.releaseStock(0));
        assertThrows(IllegalArgumentException.class, () -> p.releaseStock(-1));
    }
}

