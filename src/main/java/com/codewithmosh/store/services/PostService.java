package com.codewithmosh.store.services;

import com.codewithmosh.store.dtos.CreatePostRequest;
import com.codewithmosh.store.dtos.PostDto;
import com.codewithmosh.store.exceptions.PostNotFountException;
import com.codewithmosh.store.mappers.PostMapper;
import com.codewithmosh.store.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    public PostRepository postRepository;

    public Iterable<PostDto> getAllPosts(){

        var  posts = postRepository.findAll();

        return posts.stream()
                .map(postMapper::toDto)
                .toList();
    }

    public PostDto getPostById(Long postId) {

        var post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new PostNotFountException();
        }

        return postMapper.toDto(post);
    }

    public PostDto createPost(CreatePostRequest postRequest) {

        var post = postMapper.toEntity(postRequest);

        postRepository.save(post);

        return postMapper.toDto(post);
    }

    public PostDto updatePost(Long postId, CreatePostRequest postRequest) {

        var post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new PostNotFountException();
        }
        postMapper.update(postRequest, post);

        postRepository.save(post);

        return postMapper.toDto(post);
    }

    public void deletePost(Long postId) {

        var post = postRepository.findById(postId).orElse(null);
        if (post == null) {
           throw new PostNotFountException();
        }
        postRepository.delete(post);
    }
}
