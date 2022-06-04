package org.codej.blog.controller;

import lombok.RequiredArgsConstructor;
import org.codej.blog.configuration.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class BoardController {


    @GetMapping({"/",""})
    public String index(){
        return "index";
    }
    @GetMapping("/board/save")
    public String saveForm(){
        return "/board/save";
    }


}
