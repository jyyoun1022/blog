package org.codej.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    //인증이 안된 사용자들이 출입할 수 있는 경로는 "/auth"
    @GetMapping("/auth/join")
    public String join(){
        return "user/join";
    }
    @GetMapping("/auth/login")
    public String login(){
        return "user/login";
    }
    @GetMapping("/user/info")
    public String info(){
        return "/user/info";
    }
}
