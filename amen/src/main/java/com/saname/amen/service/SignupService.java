package com.saname.amen.service;

import com.saname.amen.dto.SignupDto;
import com.saname.amen.model.User;
import com.saname.amen.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.Map;

@Service
public class SignupService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    SignupService(UserRepository userRepository,PasswordEncoder passwordEncoder){

        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    // 회원가입 시, 유효성 체크
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    //username 중복 여부 체크
    public Boolean usernameCheck(String username){
        System.out.println("2");
        Map<String, String> validatorResult = new HashMap<>();
        User user = userRepository.findByUsername(username)
                .orElse(null);
        return user != null;
    }

    public Boolean pwCheck(String pw1,String pw2){
        return pw1.equals(pw2);
    }






    // 회원가입
    public void signUp(SignupDto signupDto) {

        String pe=passwordEncoder.encode(signupDto.getPassword());
        User user = new User(signupDto,pe);
        userRepository.save(user);

        // 회원 가입 비즈니스 로직 구현
    }


}
