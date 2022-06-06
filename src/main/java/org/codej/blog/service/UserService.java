package org.codej.blog.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codej.blog.model.RoleType;
import org.codej.blog.model.User;
import org.codej.blog.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void join(User user){

        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);

        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);

    }
//    @Transactional(readOnly = true)
//    public  User login(User user){
//        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//    }
}
