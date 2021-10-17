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
public class KakaoUserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public KakaoUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void kakaoLogin(String code) throws JsonProcessingException {
        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getAccessToken(code);
        // 2. "액세스 토큰"으로 "카카오 사용자 정보" 가져오기
        SnsUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);
        // 3. "카카오 사용자 정보"로 필요시 회원가입
        User kakaoUser = registerSnsUserIfNeeded(kakaoUserInfo);
        // 4. 강제 로그인 처리
        forceLogin(kakaoUser);
    }


    private String getAccessToken(String code) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "e6b3946cda0f744a73cbbb4de2be3732");
        body.add("redirect_uri", "http://localhost:8080/user/kakao/callback");
        body.add("code", code);
        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    private SnsUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();
        String email = jsonNode.get("kakao_account")
                .get("email").asText();
        return new SnsUserInfoDto(id, nickname, email);
    }


    private User registerSnsUserIfNeeded(SnsUserInfoDto snsUserInfoDto) {
        // DB 에 중복된 Kakao Id 가 있는지 확인
        Long snsId = snsUserInfoDto.getId();
        String snsEmail = snsUserInfoDto.getEmail();
        User snsUser = userRepository.findBySnsId(snsId)
                .orElse(null);
        User userInfo = userRepository.findByEmail(snsEmail)
                .orElse(null);
        if (snsUser == null) {
            if (userInfo == null) {
                // 회원가입
                // username: kakao nickname
                String username = snsUserInfoDto.getUsername();
                // password: random UUID
                String password = UUID.randomUUID().toString();
                String encodedPassword = passwordEncoder.encode(password);
                // email: kakao email
                String email = snsUserInfoDto.getEmail();
                snsUser = new User(username, email, encodedPassword, snsId);
                userRepository.save(snsUser);
            } else {
                userInfo.setSnsId(snsId);
                userRepository.save(userInfo);
                return userInfo;
            }
        }
        return snsUser;
    }

    private void forceLogin(User snsUser) {
        UserDetails userDetails = new UserDetailsImpl(snsUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}