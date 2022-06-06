package org.codej.blog.repository;

import org.codej.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //select * from user where username = 1?;
    Optional<User> findByUsername(String username);



}


/*로그인을 위한 함수*/
/* JPA Naming 쿼리 전략 */
/* select * from user where username=?1 and password=?2; */
//    User findByUsernameAndPassword(String username,String password);

//    @Query(value = "select * from user where username=?1 and password=?2",nativeQuery = true)
//    User login(String username,String password);


