package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.web.dto.PostDto;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    @Override
    public List<Post> findLatest5() {
        return this.postRepository.findLatest5Posts(new PageRequest(0, 5));
    }

    @Override
    public Post findById(Long id) {
        return this.postRepository.findOne(id);
    }

    @Override
    public Post create(PostDto postdto) {
        return this.postRepository.save(preparePost(postdto));
    }

    private Post preparePost(PostDto postDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       // JSONArray jsonArray = new JSONArray(postDto.getBody());


        return new Post(postDto.getTitle()
                , postDto.getBody()
                , userService.findByEmail(userDetails.getUsername())
                , new Date(),
                postDto.getBody().substring(0,Math.min(postDto.getBody().length(),40)));
    }

    public Post findByTitle(String title) {
        return this.postRepository.findByTitle(title);
    }

    public Post create(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public Post edit(PostDto postdto) {
        return this.postRepository.save(preparePost(postdto));
    }

    @Override
    public void deleteById(Long id) {
        this.postRepository.delete(id);
    }
}
