package com.saname.amen.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saname.amen.dto.SnsUserInfoDto;
import com.saname.amen.model.User;
import com.saname.amen.repository.UserRepository;
import com.saname.amen.security.UserDetailsImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class GithubUserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public GithubUserService(UserRepository userRepository, PasswordEncoder passwordEncoder){

        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public void githubLogin(String code) throws JsonProcessingException {
        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getAccessToken(code);

        // 2. "액세스 토큰"으로 "카카오 사용자 정보" 가져오기
        SnsUserInfoDto githubUserInfo = getGithubUserInfo(accessToken);
//        // 3. "카카오 사용자 정보"로 필요시 회원가입
        User githubUser = registerSnsUserIfNeeded(githubUserInfo);
//        // 4. 강제 로그인 처리
        forceLogin(githubUser);
    }




    private String getAccessToken(String code) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.add("Accept", "application/json");


        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_secret", "");
        body.add("client_id", "");
        body.add("redirect_uri", "http://localhost:8080/user/github/callback");
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> githubTokenRequest =
                new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.exchange(
                "https://github.com/login/oauth/access_token",
                HttpMethod.POST,
                githubTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);




        return jsonNode.get("access_token").asText();


    }

    private SnsUserInfoDto getGithubUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> githubUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://api.github.com/user",
                HttpMethod.GET,
                githubUserInfoRequest,
                String.class
        );
        String responseBody = response.getBody();

        System.out.println("responseBody = " + responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("login").asText();
        String email = jsonNode.get("html_url").asText();

        return new SnsUserInfoDto(id, nickname, email);

    }

    private User registerSnsUserIfNeeded(SnsUserInfoDto snsUserInfoDto) {
        // DB 에 중복된 Id 가 있는지 확인
        Long snsId = snsUserInfoDto.getId();
        String snsEmail = snsUserInfoDto.getEmail();
        User snsUser = userRepository.findBySnsId(snsId)
                .orElse(null);
        User userInfo = userRepository.findByEmail(snsEmail)
                .orElse(null);
        if (snsUser == null) {
            if (userInfo == null){
                // 회원가입
                // username: nickname
                String username = snsUserInfoDto.getUsername();
                // password: random UUID
                String password = UUID.randomUUID().toString();
                String encodedPassword = passwordEncoder.encode(password);
                // email:  github 주소
                String email = snsUserInfoDto.getEmail();
                snsUser = new User(username, email,encodedPassword,snsId);
                userRepository.save(snsUser);
            }else{
                userInfo.setSnsId(snsId);
                userRepository.save(userInfo);
                return userInfo;
            }
        }
        return snsUser;
    }

    private void forceLogin(User snsUser) {
        UserDetails userDetails = new UserDetailsImpl(snsUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()) ;
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
