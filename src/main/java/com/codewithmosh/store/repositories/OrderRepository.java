package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
