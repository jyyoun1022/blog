package org.codej.blog.controller.api;

import lombok.RequiredArgsConstructor;
import org.codej.blog.dto.ResponseDTO;
import org.codej.blog.model.RoleType;
import org.codej.blog.model.User;
import org.codej.blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/user")
    public ResponseDTO<Integer> save(@RequestBody User user){
        System.out.println("UserApiController : save 호출됨");
        user.setRole(RoleType.USER);
         userService.join(user);
        return new ResponseDTO<Integer>(HttpStatus.OK,1);
    }
    @PostMapping("/api/user/login")
    public ResponseDTO<Integer> login(@RequestBody User user, HttpSession session){
        System.out.println("UserApiController : login 호출됨");
        User principal = userService.login(user);

        if(principal != null){
            session.setAttribute("principal",principal);
        }
        return new ResponseDTO<>(HttpStatus.OK,1);
    }
}