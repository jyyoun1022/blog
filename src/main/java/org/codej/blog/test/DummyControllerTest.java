package org.codej.blog.test;

import lombok.RequiredArgsConstructor;
import org.codej.blog.model.RoleType;
import org.codej.blog.model.User;
import org.codej.blog.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

@RestController
@RequiredArgsConstructor
public class DummyControllerTest {

    private final UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id : = " +user.getId());
        System.out.println("username = " + user.getUsername());
        System.out.println("password = " + user.getPassword());
        System.out.println("email = " + user.getEmail());
        System.out.println("role"+user.getRole());
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료 되었습니다.";

    }
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable Long id){

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id= " + id);
            }
        });
        return user;
    }
}
