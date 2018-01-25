package com.example.demo.web;

import com.example.demo.service.PostServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
public class MainController {

    private PostServiceImpl postService;

    public MainController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String root(Model model) {
        //UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //String userDetails = SecurityContextHolder.getContext().getAuthentication().getName();
        if (SecurityContextHolder.getContext().getAuthentication().getName().toLowerCase().equals("anonymoususer")) {
            model.addAttribute("username", "ANONIM");
        } else {
            UserDetails userDetailsObj = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("username", userDetailsObj.getUsername());
        }

        model.addAttribute("postek", postService.findLatest5());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginSucc(Model model){return "redirect:/";}
/*
    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }*/
}