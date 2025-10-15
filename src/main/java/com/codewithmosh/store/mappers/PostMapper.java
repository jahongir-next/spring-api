package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.CreatePostRequest;
import com.codewithmosh.store.dtos.PostDto;
import com.codewithmosh.store.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto toDto(Post post);

    Post toEntity(CreatePostRequest request);

//    @Mapping(target = "id",  ignore = true)
    void update(CreatePostRequest request, @MappingTarget Post post);
}
