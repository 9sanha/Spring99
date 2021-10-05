package com.sparta.week04.service;

import com.sparta.week04.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    private final UserRepository userRepository;

    @Autowired
    public SignupService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
