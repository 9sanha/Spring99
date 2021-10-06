package com.sparta.week04.dto;

import com.sparta.week04.models.User;
import com.sparta.week04.repository.UserRepository;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Optional;

@Setter
@Getter
@ToString
public class SignupRequestDto {

    UserRepository userRepository;

    @NotBlank(message = "사용자명은 필수 입력 값입니다.")
    @Pattern(regexp= "^[a-zA-Z0-9]{4,}$",
            message = "사용자명은 영문 대,소문자와 숫자로 이루어진 3자 이상 닉네임이어야 합니다.")
    private String username;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp= "^[a-zA-Z0-9]{4,}$",
            message = "비밀번호는 영문 대,소문자와 숫자로 이루어진 4자 이상의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호를 한 번 더 적어야 합니다.")
    private String passwordCheck;

//    @AssertTrue(message = "비밀번호가 일치하지 않습니다.")
//    private boolean ispwSame;
//    private boolean isPwSame(){
//        if (password.equals(passwordCheck)){
//            ispwSame=passwordCheck.equals(password);
//            return true;}
//        else{
//            return false;
//        }
//    }

    @AssertFalse(message = "비밀번호에 닉네임 값이 포함되어 있을 수 없습니다.")
    private boolean ispwIncludeId;
    private boolean isPwIncludeId(){
        ispwIncludeId=this.password.contains(this.username);
        return ispwIncludeId;
    }

    @AssertFalse(message = "해당 사용자명이 이미 존재합니다.")
    private boolean isidSame;
    private boolean isIdSame(){
        isidSame= userRepository.findByUsername(this.username).isPresent();
        //값이 있으면 true 반환
        return isidSame;

    }

    @Builder
    public SignupRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }
}