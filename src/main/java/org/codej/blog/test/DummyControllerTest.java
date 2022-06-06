package org.codej.blog.test;

import lombok.RequiredArgsConstructor;
import org.codej.blog.model.RoleType;
import org.codej.blog.model.User;
import org.codej.blog.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequiredArgsConstructor
public class DummyControllerTest {

    private final UserRepository userRepository;

    @GetMapping("/dummy/list")
    public List<User> userList(){
        return userRepository.findAll();
    }
    @GetMapping("/dummy/user")
    public Page<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        List<User> user = users.getContent();
        return users;
    }
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User requestUser){
        System.out.println("id = "+id);
        System.out.println("password = "+requestUser.getPassword());
        System.out.println("email =" +requestUser.getEmail());
        requestUser.setId(id);
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        userRepository.save(user);
        return user;

    }
    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable Long id){
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return "삭제 실패 ";
        }
        return "삭제되었습니다.id = "+id;
    }


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

//        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해당 유저는 없습니다. id= " + id);
//            }
//        });

        //람다식
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 사용자는 없습니다 id = " + id);
        });
        return user;
    }
}
