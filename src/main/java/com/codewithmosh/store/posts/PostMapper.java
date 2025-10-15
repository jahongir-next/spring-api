package com.codewithmosh.store.posts;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto toDto(Post post);

    Post toEntity(CreatePostRequest request);

//    @Mapping(target = "id",  ignore = true)
    void update(CreatePostRequest request, @MappingTarget Post post);
}
