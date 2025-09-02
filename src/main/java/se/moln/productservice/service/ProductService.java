package se.moln.productservice.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.moln.productservice.dto.ProductRequest;
import se.moln.productservice.dto.ProductResponse;
import se.moln.productservice.mappning.ProductMapper;
import se.moln.productservice.model.Category;
import se.moln.productservice.model.Product;
import se.moln.productservice.repository.CategoryRepository;
import se.moln.productservice.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository repo;
    private final CategoryRepository catRepo;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repo, CategoryRepository catRepo, ProductMapper mapper){
        this.repo = repo;
        this.catRepo = catRepo;
        this.mapper = mapper;
    }

    @Transactional
    public ProductResponse create(ProductRequest req){
        Category cat = req.categoryId()!=null ? catRepo.findById(req.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found")) : null;

        Product entity = mapper.toEntity(req, cat);

        if (repo.existsBySlug(entity.getSlug())){
            entity.setSlug(entity.getSlug());
        }
        return mapper.toResponse(repo.save(entity));
    }
}
