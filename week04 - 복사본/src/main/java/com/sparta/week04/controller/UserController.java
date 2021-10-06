package com.sparta.week04.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.week04.dto.SignupRequestDto;
import com.sparta.week04.service.KakaoUserService;
import com.sparta.week04.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {

    private final SignupService signupService;

    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(KakaoUserService kakaoUserService,SignupService signupService) {

        this.signupService = signupService;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(SignupRequestDto signupRequestDto) {

        return "/signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Valid SignupRequestDto signupRequestDto, Errors errors, Model model) {
        System.out.println(errors);
        System.out.println(signupRequestDto.getPasswordCheck());
        System.out.println(signupRequestDto.getPassword());
        if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("SignupRequestDto", signupRequestDto);
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = signupService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/signup";
        }else {
            signupService.signUp(signupRequestDto);
            return "redirect:/user/login";
        }
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}