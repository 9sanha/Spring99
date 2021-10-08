package com.saname.amen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AmenApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmenApplication.class, args);
    }

}
