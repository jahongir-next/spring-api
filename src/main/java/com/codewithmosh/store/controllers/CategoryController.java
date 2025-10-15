package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.CategoryDto;
import com.codewithmosh.store.dtos.CreateCategoryRequest;
import com.codewithmosh.store.exceptions.CategoryNotFoundException;
import com.codewithmosh.store.mappers.CategoryMapper;
import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    public CategoryRepository categoryRepository;

    @GetMapping("")
    public Iterable<CategoryDto> findAll() {

        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public CategoryDto findById(
            @PathVariable Byte categoryId
    ) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("")
    public ResponseEntity<CategoryDto> createCategory(
            @Valid @RequestBody CreateCategoryRequest request,
            UriComponentsBuilder uriBuilder
    ) {

        var categoryDto =  categoryService.createCategory(request);

        var uri = uriBuilder.path("/categories/{id}").buildAndExpand(categoryDto.getId()).toUri();

        return ResponseEntity.created(uri).body(categoryDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @Valid @RequestBody CreateCategoryRequest request,
            @PathVariable(name = "id") Byte id

    ){
        var categoryDto =  categoryService.updateCategory(id, request);

        return ResponseEntity.ok(categoryDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id") Byte id){

        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Category was not found."));
    }

}
