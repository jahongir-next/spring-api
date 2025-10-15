package com.codewithmosh.store.products;

import com.codewithmosh.store.carts.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    Product toEntity(CreateProductRequest request);

//    @Mapping(target = "id", ignore = true)
    Product update(CreateProductRequest request, @MappingTarget Product product);

}
