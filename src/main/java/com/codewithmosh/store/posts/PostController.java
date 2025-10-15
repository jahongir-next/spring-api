package com.codewithmosh.store.posts;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostMapper postMapper;
    private final PostService postService;
    private PostRepository postRepository;


    @GetMapping("")
    public Iterable<PostDto> getAllPosts() {

        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {

        var postDto = postService.getPostById(id);

        return ResponseEntity.ok(postDto);
    }

    @PostMapping("")
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody CreatePostRequest request,
            UriComponentsBuilder uriBuilder
    ){
        var postDto = postService.createPost(request);

        var uri = uriBuilder.path("/posts/{id}").buildAndExpand(postDto.getId()).toUri();

        return ResponseEntity.created(uri).body(postDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable(name = "id") Long id,
            @RequestBody CreatePostRequest request
    ){
      var postDto = postService.updatePost(id, request);

      return ResponseEntity.ok(postDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable(name = "id") Long id){

        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(PostNotFountException.class)
    public ResponseEntity<Map<String, String>> handlePostNotFound(){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Post was not found."));
    }

}
