package com.nhnacademy.springboot.gatewayserver.controller;

import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.MemberDetailsDto;
import com.nhnacademy.springboot.gatewayserver.domain.MemberRequest;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.LoginDto;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.TokenInformation;
import com.nhnacademy.springboot.gatewayserver.service.memberapi.MemberService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "form/loginForm";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestBody LoginDto loginDto){ return memberService.tryLogin(loginDto); }

    @GetMapping("/github")
    void LoginToGithub(HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
        response.sendRedirect(memberService.tryToLoginAtGithub());
    }

    @GetMapping("/login/oauth2/code/github")
    String doLogin(@RequestParam String code, @RequestParam String state,
                   HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws ServletException, IOException {
        TokenInformation token = memberService.getToken(code, state);
        MemberDetailsDto memberDetailsDto = memberService.requestEmail(token);
        return memberService.checkEmail(memberDetailsDto,request,authentication, response);
    }

    @GetMapping("/member/register")
    public String register() {
        return "form/registerForm";
    }

    @PostMapping("/member/register")
    public String doRegister(MemberRequest memberRequest) {
        memberService.register(memberRequest);
        return "index";
    }
}
