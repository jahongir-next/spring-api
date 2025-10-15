package com.codewithmosh.store.products;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;

    public Iterable<CategoryDto> getAllCategories() {

        var categories =  categoryRepository.findAll();

        return categories.stream().map(categoryMapper::toDto).toList();
    }

    public CategoryDto getCategoryById(Byte categoryId) {
        var category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            throw new CategoryNotFoundException();
        }

        return categoryMapper.toDto(category);
    }

    public CategoryDto createCategory(CreateCategoryRequest request) {

        var category = categoryMapper.toEntity(request);
        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    public CategoryDto updateCategory(Byte categoryId, CreateCategoryRequest request) {

        var category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException();
        }

        categoryMapper.update(request , category);

        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    public void deleteCategory(Byte categoryId) {
        var category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException();
        }

        categoryRepository.delete(category);
    }
}
