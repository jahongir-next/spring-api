package com.codewithmosh.store.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull
    @Positive(message = "Price must be positive.")
    private BigDecimal price;

    private String description;

    @NotNull(message = "Category must be selected.")
    private Byte categoryId;
}
