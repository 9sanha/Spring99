package com.saname.amen.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saname.amen.dto.SignupDto;
import com.saname.amen.security.UserDetailsImpl;
import com.saname.amen.service.KakaoUserService;
import com.saname.amen.service.SignupService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    //인가코드 요청 후 callback url
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        // 인가코드로 토큰 요청
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }

    // @AuthenticationPrincipal ??????????????????????????????????????????????????????????
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
            // 발생한 오류 메시지르 {오류난곳:오류메시지} 형식으로 model에 저장
            for (String key : validatorResult.keySet()) {

                model.addAttribute(key, validatorResult.get(key));
            }
            return "/signup";
        }
        //회원가입 정보를 서비스에 전달
        signupService.signUp(signupDto);
        return "redirect:/user/login";
    }


}
