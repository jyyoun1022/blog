package org.codej.blog.controller.api;

import lombok.RequiredArgsConstructor;
import org.codej.blog.configuration.auth.PrincipalDetail;
import org.codej.blog.dto.ResponseDTO;
import org.codej.blog.model.RoleType;
import org.codej.blog.model.User;
import org.codej.blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder;
//    private final HttpSession session;

    @PostMapping("/auth/join")
    public ResponseDTO<Integer> save(@RequestBody User user){
        System.out.println("UserApiController : save 호출됨");
         userService.join(user);
        return new ResponseDTO<Integer>(HttpStatus.OK.value(),1);
    }
    /**해당 부분은 시큐리티 이용할 것이라서 주석처리*/
//    @PostMapping("/api/user/login")
//    public ResponseDTO<Integer> login(@RequestBody User user){
//        System.out.println("UserApiController : login 호출됨");
//        User principal = userService.login(user);
//
//        if(principal != null){
//            session.setAttribute("principal",principal);
//        }
//        return new ResponseDTO<>(HttpStatus.OK,1);
//    }
    @PutMapping("/user")
    public ResponseDTO<Integer>update(@RequestBody User user){
        userService.update(user);
        //여기서는 트랜잭션이 종료되기 떄문에 DB에 값은 변경이 됨.
        //하지만 세션값은 변경되지 않았으므로
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword() ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDTO<>(HttpStatus.OK.value(), 1);
    }



}
