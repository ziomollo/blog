package com.example.demo.web;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    public UserController() {
    }

    @GetMapping("/user")
    public String user(Model model){
        String x = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username",x);
        return "user";
    }
}
