package com.sparta.myposting.controller;


import com.sparta.myposting.webSecurity.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            return "index";
        }
        model.addAttribute("username", "λΉνμ");
        return "index";
    }
}