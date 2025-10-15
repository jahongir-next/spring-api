package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.CategoryDto;
import com.codewithmosh.store.dtos.CreateCategoryRequest;
import com.codewithmosh.store.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest request);

    void update(CreateCategoryRequest request, @MappingTarget Category category);
}
