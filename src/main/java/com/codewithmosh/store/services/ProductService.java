package com.codewithmosh.store.services;

import com.codewithmosh.store.dtos.CreateProductRequest;
import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.exceptions.CategoryNotFoundException;
import com.codewithmosh.store.exceptions.ProductNotFoundException;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    private ProductMapper productMapper;

    public Iterable<ProductDto> getAllProducts(Byte catedoryId) {

        List<Product> products;

        if (catedoryId != null) {
            products = productRepository.findByCategoryId(catedoryId);
        }else{
            products = productRepository.findAllWithCategories();
        }

        return products
                .stream()
                .map(productMapper::toDto)
                .toList();

    }

    public ProductDto getProductById(Long productId) {

        var product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return productMapper.toDto(product);
    }

    public ProductDto createProduct(CreateProductRequest request) {

        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);

        if (category == null) {
            throw new CategoryNotFoundException();
        }

        var product = productMapper.toEntity(request);
        product.setCategory(category);

        return productMapper.toDto(productRepository.save(product));

    }

    public ProductDto updateProduct(Long productId, CreateProductRequest request) {
        var product = productRepository.findById(productId).orElse(null);
        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        if (category == null) {
            throw new CategoryNotFoundException();
        }

        //productMapper.update(request , product);

        product.setName(request.getName() != null ? request.getName() : product.getName());
        product.setPrice(request.getPrice() != null ? request.getPrice() : product.getPrice());
        product.setDescription(request.getDescription() != null ? request.getDescription() : product.getDescription());

        product.setCategory(category);

        return productMapper.toDto(productRepository.save(product));
    }

    public void deleteProduct(Long productId) {
        var product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException();
        }

        productRepository.delete(product);

    }
}
