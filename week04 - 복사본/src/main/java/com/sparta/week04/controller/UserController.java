package com.sparta.week04.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.sparta.week04.dto.SignupRequestDto;
import com.sparta.week04.repository.UserRepository;
import com.sparta.week04.service.KakaoUserService;
import com.sparta.week04.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    //아이디 중복 확인
    @GetMapping("user/checkId/{username}")
    public boolean checkId(@PathVariable String username ){
        //아이디가 중복 되지 않으면 true / 중복되면 false
        return userService.userIdCheck(username);

    }



    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }


    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}