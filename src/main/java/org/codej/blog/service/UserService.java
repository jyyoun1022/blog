package org.codej.blog.service;

import lombok.RequiredArgsConstructor;
import org.codej.blog.model.User;
import org.codej.blog.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void join(User user){

            userRepository.save(user);

    }
    @Transactional(readOnly = true)
    public  User login(User user){
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
