package com.saname.amen.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saname.amen.dto.SignupDto;
import com.saname.amen.security.UserDetailsImpl;
import com.saname.amen.service.KakaoUserService;
import com.saname.amen.service.SignupService;
import lombok.Getter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {
    private final KakaoUserService kakaoUserService;
    private final SignupService signupService;

    UserController(SignupService signupService,KakaoUserService kakaoUserService){

        this.signupService=signupService;
        this.kakaoUserService = kakaoUserService;
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }

    @GetMapping("/user/login")
    public String loginHome(@AuthenticationPrincipal UserDetailsImpl userDetails){


        return "login";
    }

    @GetMapping("/user/signup")
    public String signup(SignupDto signupDto){
        return "signup";
    }

    @PostMapping("/user/signup")
    public String execSignup(@Validated SignupDto signupDto, BindingResult errors, Model model) {

        //pw 같은지
        Boolean isSamePw=signupService.pwCheck(signupDto.getPassword(),signupDto.getPasswordCheck());

        //username 중복검사
        Boolean isSameUsername = signupService.usernameCheck(signupDto.getUsername());
        if (isSameUsername|errors.hasErrors()|!isSamePw){
            Map<String, String> validatorResult=new HashMap<>();
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("signupDto", signupDto);
            if (!isSamePw) {
                String validKeyNamePw = "valid_passwordCheck";
                String valivalPw = "패스워드가 동일하지 않습니다.";
                validatorResult.put(validKeyNamePw, valivalPw);
            }
            if (isSameUsername){
                String validKeyName = "valid_username";
                String valival = "입력하신 사용자 이름은 이미 존재합니다";
                validatorResult.put(validKeyName,valival);
            }
            if (errors.hasErrors()) {
                // 유효성 통과 못한 필드와 메시지를 핸들링
                validatorResult.putAll(signupService.validateHandling(errors));
            }
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/signup";
        }

        signupService.signUp(signupDto);
        return "redirect:/user/login";
    }

}
