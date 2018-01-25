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
        userRegistrationDto.setEmail("user@mail.com");
        userRegistrationDto.setFirstName("FirstName");
        userRegistrationDto.setLastName("LastName");
        userRegistrationDto.setConfirmEmail("user@mail.com");
        userRegistrationDto.setPassword("password");
        userRegistrationDto.setConfirmPassword("password");
        userRegistrationDto.setTerms(true);


        this.userService.save(userRegistrationDto);

        User user = this.userService.findByEmail("user@mail.com");

        Post post = new Post();
        post.setAuthor(user);
        post.setBody("The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog." +
                " Junk MTV quiz graced by fox whelps. Bawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! " +
                "Fox nymphs grab quick-jived waltz. Brick quiz whangs jumpy veldt fox. Bright vixens jump; dozy fowl quack." +
                " Quick wafting zephyrs vex bold Jim. Quick zephyrs blow, vexing daft Jim. Sex-charged fop blew my junk TV quiz." +
                " How quickly daft jumping zebras vex. Two driven jocks help fax my big quiz. Quick, Baz, get my woven flax jodhpurs!" +
                " \"Now fax quiz Jack!\" my brave");
        post.setDate(new Date());
        post.setTitle("Dummy text");
        post.setPreview(post.getBody().substring(0,Math.min(post.getBody().length(),40)));

        this.postService.create(post);
    }
}
