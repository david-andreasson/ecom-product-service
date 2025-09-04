package se.moln.productservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;
import se.moln.productservice.model.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProductSpecificationsTest {

    @Test
    void hasNameLike_ShouldCreateNonNullSpecification() {
        // Given
        String searchName = "iPhone";

        // When
        Specification<Product> spec = ProductSpecifications.hasNameLike(searchName);

        // Then
        assertThat(spec).isNotNull();
    }

    @Test
    void hasNameLike_WithEmptyString_ShouldCreateNonNullSpecification() {
        // Given
        String searchName = "";

        // When
        Specification<Product> spec = ProductSpecifications.hasNameLike(searchName);

        // Then
        assertThat(spec).isNotNull();
    }

    @Test
    void hasNameLike_WithNullString_ShouldCreateNonNullSpecification() {
        // Given
        String searchName = null;

        // When
        Specification<Product> spec = ProductSpecifications.hasNameLike(searchName);

        // Then
        assertThat(spec).isNotNull();
    }

    @Test
    void hasCategoryName_ShouldCreateNonNullSpecification() {
        // Given
        String categoryName = "Electronics";

        // When
        Specification<Product> spec = ProductSpecifications.hasCategoryName(categoryName);

        // Then
        assertThat(spec).isNotNull();
    }

    @Test
    void hasCategoryName_WithEmptyString_ShouldCreateNonNullSpecification() {
        // Given
        String categoryName = "";

        // When
        Specification<Product> spec = ProductSpecifications.hasCategoryName(categoryName);

        // Then
        assertThat(spec).isNotNull();
    }

    @Test
    void hasPriceBetween_WithBothPrices_ShouldCreateNonNullSpecification() {
        // Given
        BigDecimal minPrice = BigDecimal.valueOf(100);
        BigDecimal maxPrice = BigDecimal.valueOf(500);

        // When
        Specification<Product> spec = ProductSpecifications.hasPriceBetween(minPrice, maxPrice);

        // Then
        assertThat(spec).isNotNull();
    }

    @Test
    void hasPriceBetween_WithOnlyMinPrice_ShouldCreateNonNullSpecification() {
        // Given
        BigDecimal minPrice = BigDecimal.valueOf(100);
        BigDecimal maxPrice = null;

        // When
        Specification<Product> spec = ProductSpecifications.hasPriceBetween(minPrice, maxPrice);

        // Then
        assertThat(spec).isNotNull();
    }

    @Test
    void hasPriceBetween_WithOnlyMaxPrice_ShouldCreateNonNullSpecification() {
        // Given
        BigDecimal minPrice = null;
        BigDecimal maxPrice = BigDecimal.valueOf(500);

        // When
        Specification<Product> spec = ProductSpecifications.hasPriceBetween(minPrice, maxPrice);

        // Then
        assertThat(spec).isNotNull();
    }

    @Test
    void hasPriceBetween_WithBothPricesNull_ShouldCreateNonNullSpecification() {
        // Given
        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;

        // When
        Specification<Product> spec = ProductSpecifications.hasPriceBetween(minPrice, maxPrice);

        // Then
        assertThat(spec).isNotNull();
    }

    @Test
    void hasPriceBetween_WithZeroPrices_ShouldCreateNonNullSpecification() {
        // Given
        BigDecimal minPrice = BigDecimal.ZERO;
        BigDecimal maxPrice = BigDecimal.ZERO;

        // When
        Specification<Product> spec = ProductSpecifications.hasPriceBetween(minPrice, maxPrice);

        // Then
        assertThat(spec).isNotNull();
    }

    @Test
    void hasPriceBetween_WithNegativePrices_ShouldCreateNonNullSpecification() {
        // Given
        BigDecimal minPrice = BigDecimal.valueOf(-100);
        BigDecimal maxPrice = BigDecimal.valueOf(-50);

        // When
        Specification<Product> spec = ProductSpecifications.hasPriceBetween(minPrice, maxPrice);

        // Then
        assertThat(spec).isNotNull();
    }

    @Test
    void fetchAttributes_ShouldCreateNonNullSpecification() {
        // When
        Specification<Product> spec = ProductSpecifications.fetchAttributes();

        // Then
        assertThat(spec).isNotNull();
    }

    @Test
    void specifications_ShouldBeCombinable() {
        // Given
        String name = "iPhone";
        String category = "Electronics";
        BigDecimal minPrice = BigDecimal.valueOf(100);
        BigDecimal maxPrice = BigDecimal.valueOf(1000);

        // When
        Specification<Product> combinedSpec = ProductSpecifications.fetchAttributes()
                .and(ProductSpecifications.hasNameLike(name))
                .and(ProductSpecifications.hasCategoryName(category))
                .and(ProductSpecifications.hasPriceBetween(minPrice, maxPrice));

        // Then
        assertThat(combinedSpec).isNotNull();
    }

    @Test
    void specifications_CanBeChainedWithOr() {
        // Given
        String name1 = "iPhone";
        String name2 = "Samsung";

        // When
        Specification<Product> combinedSpec = ProductSpecifications.hasNameLike(name1)
                .or(ProductSpecifications.hasNameLike(name2));

        // Then
        assertThat(combinedSpec).isNotNull();
    }

    @Test
    void specifications_CanBeNegated() {
        // Given
        String categoryName = "Electronics";

        // When
        Specification<Product> negatedSpec = Specification.not(ProductSpecifications.hasCategoryName(categoryName));

        // Then
        assertThat(negatedSpec).isNotNull();
    }

}
