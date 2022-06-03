package org.codej.blog.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class EncTest {

    @Test
    public void hash_test(){
        String encPWD = new BCryptPasswordEncoder().encode("1234");
        System.out.println("encPWD의 해쉬값 = " + encPWD);
        //1번째 : $2a$10$dHlU6CHAqFkFKBPwW8Ea4eMPrvxv.z1Vcdag.oihqd3pW9B5jwx8G
        //2번째 : $2a$10$P.HMwrZ3.FSwZXR8DZFAGeuWpCJ.fyL4fSrAtGhw.uDdeePncf6rK
    }
}
