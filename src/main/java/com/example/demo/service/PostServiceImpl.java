package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.web.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class PostServiceImpl implements PostService  {

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
        return this.postRepository.findLatest5Posts(new PageRequest(0,5));
    }

    @Override
    public Post findById(Long id) {
        return this.postRepository.findOne(id);
    }

    @Override
    public Post create(PostDto postdto) {
        return this.postRepository.save(preparePost(postdto));
    }

    private Post preparePost(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setDate(new Date());
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setAuthor(userService.findByEmail(userDetails.getUsername()));

        return post;
    }

    public Post findByTitle(String title){
        return this.postRepository.findByTitle(title);
    }

    public Post create(Post post){
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
