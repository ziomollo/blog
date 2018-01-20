package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.web.dto.UserRegistrationDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DbSeeder implements CommandLineRunner {

    private UserServiceImpl userService;

    private PostServiceImpl postService;

    public DbSeeder(UserServiceImpl userService, PostServiceImpl postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public void run(String... args) throws Exception {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setEmail("janusz@gmail.com");
        userRegistrationDto.setFirstName("Janek");
        userRegistrationDto.setLastName("Janek");
        userRegistrationDto.setConfirmEmail("janusz@gmail.com");
        userRegistrationDto.setPassword("123");
        userRegistrationDto.setConfirmPassword("123");
        userRegistrationDto.setTerms(true);


        this.userService.save(userRegistrationDto);

        User user = this.userService.findByEmail("janusz@gmail.com");

        Post post = new Post();
        post.setAuthor(user);
        post.setBody("BLABLABLA");
        post.setDate(new Date());
        post.setTitle("NAJLEPSZY POST EVER");

        this.postService.create(post);
    }
}
