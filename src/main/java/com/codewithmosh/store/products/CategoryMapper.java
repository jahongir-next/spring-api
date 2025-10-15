package com.codewithmosh.store.products;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest request);

    void update(CreateCategoryRequest request, @MappingTarget Category category);
}
