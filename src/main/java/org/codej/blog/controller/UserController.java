package org.codej.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class UserController {

    @GetMapping("/user/join")
    public String join(){
        return "user/join";
    }
    @GetMapping("/user/login")
    public String login(){
        return "user/login";
    }
}
