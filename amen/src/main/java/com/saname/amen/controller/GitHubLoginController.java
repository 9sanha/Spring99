package com.saname.amen.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saname.amen.service.GithubUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GitHubLoginController {

    private final GithubUserService githubUserService;

    public GitHubLoginController(GithubUserService githubUserService) {
        this.githubUserService = githubUserService;
    }

    @GetMapping("/user/github/callback")
    public String getToken(@RequestParam String code) throws JsonProcessingException{
        System.out.println("code: "+code);
        githubUserService.githubLogin(code);
        return "index";
    }


}
