package com.example.demo.web;

import com.example.demo.model.Post;
import com.example.demo.service.PostServiceImpl;
import com.example.demo.web.dto.PostDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/post")
public class PostController {

    private PostServiceImpl postService;

    @ModelAttribute("post")
    public PostDto post(){ return new PostDto();}

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping
    public String p(Model model){
        return "creatingPost";
    }

    @PostMapping
    public String addPost(@ModelAttribute("post") @Valid PostDto post, BindingResult result){

        Post existing = postService.findByTitle(post.getTitle());
        if( existing != null){
            result.rejectValue("Title", null, "There is already post with that title");
        }
        if( result.hasErrors()){
            return "creatingPost";
        }

        this.postService.create(post);

        return "redirect:/";
    }

    @GetMapping("/{title}")
    public String getPost(Model model, @PathVariable("title") String title){
        Post existing = postService.findByTitle(title);
       // existing.setPreview(existing.getBody().substring(0,Math.min(existing.getBody().length(),50)));
        model.addAttribute("post",existing);

        return "post";
    }
}
