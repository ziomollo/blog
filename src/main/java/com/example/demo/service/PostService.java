package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.web.dto.PostDto;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    List<Post> findLatest5();
    Post findById(Long id);
    Post create(PostDto post);
    Post edit(PostDto post);
    void deleteById(Long id);
}
