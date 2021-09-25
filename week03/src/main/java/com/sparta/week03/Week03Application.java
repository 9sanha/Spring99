package com.sparta.week03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //생성, 수정시간 변경시 자동 업데이트됨
@SpringBootApplication
public class Week03Application {

    public static void main(String[] args) {
        SpringApplication.run(Week03Application.class, args);
    }
//9616
}
