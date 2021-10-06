package com.sparta.week04.service;

import com.sparta.week04.dto.SignupRequestDto;
import com.sparta.week04.models.User;
import com.sparta.week04.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class SignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public SignupService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입 시, 유효성 체크
    public Map<String, String> validateHandling(Errors errors) {

        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            // signupDto 필드명 / error.getField() 유효성 검사에 실패한 필드 목록
            String validKeyName = String.format("valid_%s", error.getField());
            //error.getDefaultMessage() dto에서 작성한 error message 값
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    //DB에 저장
    public void signUp(SignupRequestDto signupRequestDto) {
        User user = new User(signupRequestDto); // 여기서 USER Enum값 들어가야 함
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        user.setPassword(password);

        userRepository.save(user);
    }
}
