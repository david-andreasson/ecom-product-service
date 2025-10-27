package se.moln.productservice.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;
import se.moln.productservice.model.Category;
import se.moln.productservice.model.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void hasCategoryName_ToPredicate_JoinsCategory() {
        var spec = ProductSpecifications.hasCategoryName("Electronics");

        Root<Product> root = mockRoot();
        Join<Product, Category> join = mockJoin();
        doReturn(join).when(root).join("category");

        CriteriaQuery<Product> query = mockCriteriaQuery();
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        Path<String> namePath = mockPath();
        when(join.<String>get("name")).thenReturn(namePath);
        Predicate pred = mock(Predicate.class);
        when(cb.equal(namePath, "Electronics")).thenReturn(pred);

        var out = spec.toPredicate(root, query, cb);
        assertThat(out).isNotNull();
        verify(root).join("category");
        verify(cb).equal(namePath, "Electronics");
    }

    @Test
    void hasNameLike_ToPredicate_UsesLowerAndLike() {
        var spec = ProductSpecifications.hasNameLike("Phone");

        Root<Product> root = mockRoot();
        Path<String> namePath = mockPath();
        when(root.<String>get("name")).thenReturn(namePath);

        CriteriaQuery<Product> query = mockCriteriaQuery();
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        Expression<String> lowerExpr = mockExpression();
        when(cb.lower(namePath)).thenReturn(lowerExpr);
        Predicate pred = mock(Predicate.class);
        when(cb.like(lowerExpr, "%phone%"))
                .thenReturn(pred);

        var out = spec.toPredicate(root, query, cb);
        assertThat(out).isNotNull();
        verify(cb).lower(namePath);
        verify(cb).like(lowerExpr, "%phone%");
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
        // Given
        var spec = ProductSpecifications.fetchAttributes();

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

    @Test
    void hasPriceBetween_BothPrices_ExecutesBetweenPredicate() {
        // Given
        BigDecimal min = BigDecimal.ONE;
        BigDecimal max = BigDecimal.TEN;
        var spec = ProductSpecifications.hasPriceBetween(min, max);

        // Mock Criteria API
        Root<Product> root = mockRoot();
        Path<BigDecimal> pricePath = mockPath();
        when(root.<BigDecimal>get("price")).thenReturn(pricePath);

        CriteriaQuery<Product> query = mockCriteriaQuery();
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);
        when(cb.between(pricePath, min, max)).thenReturn(predicate);

        // When
        var out = spec.toPredicate(root, query, cb);

        // Then
        assertThat(out).isNotNull();
        verify(cb).between(pricePath, min, max);
    }

    @Test
    void hasPriceBetween_OnlyMin_ExecutesGtePredicate() {
        // Given
        BigDecimal min = BigDecimal.ONE;
        var spec = ProductSpecifications.hasPriceBetween(min, null);

        // Mock Criteria API
        Root<Product> root = mockRoot();
        Path<BigDecimal> pricePath = mockPath();
        when(root.<BigDecimal>get("price")).thenReturn(pricePath);

        CriteriaQuery<Product> query = mockCriteriaQuery();
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);
        when(cb.greaterThanOrEqualTo(pricePath, min)).thenReturn(predicate);

        // When
        var out = spec.toPredicate(root, query, cb);

        assertThat(out).isNotNull();
        verify(cb).greaterThanOrEqualTo(pricePath, min);
    }

    @Test
    void hasPriceBetween_OnlyMax_ExecutesGtePredicate() {
        // Given
        BigDecimal max = BigDecimal.TEN;
        var spec = ProductSpecifications.hasPriceBetween(null, max);

        // Mock Criteria API
        Root<Product> root = mockRoot();
        Path<BigDecimal> pricePath = mockPath();
        when(root.<BigDecimal>get("price")).thenReturn(pricePath);

        CriteriaQuery<Product> query = mockCriteriaQuery();
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);
        when(cb.greaterThanOrEqualTo(pricePath, max)).thenReturn(predicate);

        // When
        var out = spec.toPredicate(root, query, cb);

        assertThat(out).isNotNull();
        verify(cb).greaterThanOrEqualTo(pricePath, max);
    }

    @Test
    void hasPriceBetween_BothNull_ExecutesConjunction() {
        // Given
        var spec = ProductSpecifications.hasPriceBetween(null, null);

        // Mock Criteria API
        Root<Product> root = mockRoot();
        CriteriaQuery<Product> query = mockCriteriaQuery();
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);
        when(cb.conjunction()).thenReturn(predicate);

        // When
        var out = spec.toPredicate(root, query, cb);

        // Then
        assertThat(out).isNotNull();
        verify(cb).conjunction();
    }

    @Test
    void fetchAttributes_ExecutesFetchAndDistinct() {
        // Given
        var spec = ProductSpecifications.fetchAttributes();

        // Mock Criteria API
        Root<Product> root = mockRoot();
        // root.fetch("attributes") returns a Fetch but we don't use it, so we can just stub the call
        when(root.fetch("attributes")).thenReturn(null);

        CriteriaQuery<Product> query = mockCriteriaQuery();
        when(query.distinct(true)).thenReturn(query);
        Predicate predicate = mock(Predicate.class);
        when(query.getRestriction()).thenReturn(predicate);
        CriteriaBuilder cb = mock(CriteriaBuilder.class);

        // When
        var out = spec.toPredicate(root, query, cb);

        // Then
        assertThat(out).isNotNull();
        verify(root).fetch("attributes");
        verify(query).distinct(true);
        verify(query).getRestriction();
    }

    @SuppressWarnings("unchecked")
    private static Root<Product> mockRoot() {
        return mock(Root.class);
    }

    @SuppressWarnings("unchecked")
    private static Join<Product, Category> mockJoin() {
        return mock(Join.class);
    }

    @SuppressWarnings("unchecked")
    private static <T> Path<T> mockPath() {
        return mock(Path.class);
    }

    @SuppressWarnings("unchecked")
    private static <T> Expression<T> mockExpression() {
        return mock(Expression.class);
    }

    @SuppressWarnings("unchecked")
    private static CriteriaQuery<Product> mockCriteriaQuery() {
        return mock(CriteriaQuery.class);
    }

}