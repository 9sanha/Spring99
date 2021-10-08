package com.saname.amen.model;

import com.saname.amen.dto.SignupDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class User extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(unique = true)
    private Long kakaoId;

    public User(SignupDto signupDto,String pe) {
        this.email=signupDto.getEmail();
        this.username=signupDto.getUsername();
        this.password=pe;



    }

    public User(String username, String email, String encodedPassword, Long kakaoId) {
        this.email = email;
        this.password =encodedPassword;
        this.username = username;
        this.kakaoId=kakaoId;
    }

    public void setKakaoId(Long kakaoId) {
        this.kakaoId=kakaoId;
    }
}
