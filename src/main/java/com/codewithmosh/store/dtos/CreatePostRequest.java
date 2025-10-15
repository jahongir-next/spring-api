package com.codewithmosh.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePostRequest {

    @NotBlank(message = "Title is required.")
    private String title;

    @NotNull(message = "Description is not null. ")
    private String body;
}
