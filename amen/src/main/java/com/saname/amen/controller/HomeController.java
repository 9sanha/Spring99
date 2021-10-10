package com.saname.amen.controller;

import com.saname.amen.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //"index" 화면 보여주기
    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){
        //로그인 정보가 없을 때 (비로그인)
        if (userDetails!= null){
            // model에 username을 user=username 형식으로 넣음
            model.addAttribute("user",userDetails.getUsername());
        }else{//로그인 정보가 있을 때 (로그인)
            // model에 -1을 user=-1 형식으로 넣음 (프론트에서 user값 비교할 때 씀)
            model.addAttribute("user",-1L);
        }
        // 위에서 user값을 넣어준 model을 view에 넣어서 함께 보냄
        return "index";
    }
}
