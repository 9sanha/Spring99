package com.sparta.springmvc;
//1주차 숙제

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String LoginPage(){
        return "redirect:/login-form.html";
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam String id, @RequestParam String password, Model model){
        if(id.equals(password)){
            //model 객체를 사용해서 view에 데이터를 전달하세요~
            model.addAttribute("loginId", id);
        }
        return "login-result";
    }
}
