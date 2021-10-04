package com.saname.myblog.controller;

import com.saname.myblog.security.UserDetailsImp;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
//    @GetMapping("/")
//    public String home(Model model) {
//
//        model.addAttribute("username", "비회원");
//        System.out.println();
//        return "index";
//    }

    @GetMapping("/")
    public String homeLongin(Model model,@AuthenticationPrincipal UserDetailsImp userDetails) {

        if (userDetails != null){
            model.addAttribute("username", userDetails.getUsername());
            System.out.println(userDetails.getUsername());
        }


        return "index";
    }
}