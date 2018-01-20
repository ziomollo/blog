package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecurityController {

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String currentUserName(Principal principal){
        return principal.getName();
    }
}
