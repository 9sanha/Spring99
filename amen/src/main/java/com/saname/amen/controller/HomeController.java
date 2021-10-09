package com.saname.amen.controller;

import com.saname.amen.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){
        if (userDetails!= null){
            model.addAttribute("user",userDetails.getUsername());
        }else{
            model.addAttribute("user",-1L);
        }
        return "index";
    }
}
