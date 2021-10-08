package com.saname.amen.dto;

import lombok.*;

import javax.validation.constraints.*;


@Setter
@Getter
@ToString
@NoArgsConstructor
public class SignupDto {
    private Long id;


    @NotBlank(message = "사용자 이름은 필수 입력 값입니다.")
    @Pattern(regexp="^*[a-zA-Z0-9]{3,}$",
            message = "비밀번호는 영문 대,소문자와 숫자, 3자 이상이여야 합니다.")
    private String username;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="^*[a-zA-Z0-9]{4,}$",
            message = "비밀번호는 영문 대,소문자와 숫자, 4자 이상의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호를 한 번 더 입력해 주세요.")
    private String passwordCheck;


//    @Builder
    public SignupDto(Long id, String username, String email, String password,String passwordCheck) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
    }
}
