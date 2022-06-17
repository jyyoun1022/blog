package org.codej.blog.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.codej.blog.model.KakaoProfile;
import org.codej.blog.model.OAuthToken;
import org.codej.blog.model.User;
import org.codej.blog.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Value("${codej.key}")
    private String rookKey;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

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
    @GetMapping("auth/kakao/callback")
    public  String kakaoCallback(String code){
        //POST 방식으로 key=value  데이터를 요청(카카오쪽으로)
        //Retrofit2,OkHttp,RestTemplate() 등등
        RestTemplate rt = new RestTemplate();
        //HttpHeader  오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        //HttpBody 오브젝트 생성
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","94b9db96f4b43cbe7457a19c4e237794");
        params.add("redirect_uri","http://localhost:8080/auth/kakao/callback");
        params.add("code",code);
        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,headers);
        //http 요청 - post 방식 - 그리고 response 변수의 응답
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken= null;
        try {
             oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("카카오 액세스 토큰 : "+oAuthToken.getAccess_token());


        RestTemplate rt2 = new RestTemplate();
        //HttpHeader  오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer "+oAuthToken.getAccess_token());
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest = new HttpEntity<>(headers2);
        //http 요청 - post 방식 - 그리고 response 변수의 응답
        ResponseEntity<String> response2 = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );
        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        //User 오브젝트 : username,password,email
        System.out.println("카카오 아이디 : "+kakaoProfile.getId());
        System.out.println("카카오 이메일 : "+kakaoProfile.getKakao_account().getEmail());
        System.out.println(kakaoProfile.getKakao_account().getProfile().profile_image_url);
        System.out.println("블로그 서버 유저네임 : "+kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        System.out.println("블로그 서버 이메일 : "+ kakaoProfile.getKakao_account().getEmail());
        System.out.println("블로그 서버 패스워드 : "+rookKey);

        User kakaoUser = User.builder()
                        .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                                .password(rookKey)
                                        .email(kakaoProfile.getKakao_account().getEmail())
                                        .oauth("kakao")
                                                .build();


        //가입자 혹은 비가입자 인지 체크 해서 처리
        User originUser = userService.findUser(kakaoUser.getUsername());
        if(originUser.getUsername() == null){
            System.out.println("기존 회원이 아닙니다.");
            userService.join(kakaoUser);
        }else {
            //로그인처리
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),rookKey));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        return "redirect:/";
    }
}
