package com.nhnacademy.springboot.gatewayserver.service.memberapi;

import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.MemberDetailsDto;
import com.nhnacademy.springboot.gatewayserver.domain.MemberRequest;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.LoginDto;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.TokenInformation;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface MemberService {
    MemberDetailsDto register(MemberRequest memberRequest);

    String tryToLoginAtGithub() throws NoSuchAlgorithmException;

    TokenInformation getToken(String code, String state);

    MemberDetailsDto requestEmail(TokenInformation token);

    String checkEmail(MemberDetailsDto memberDetailsDto, HttpServletRequest request,
                      Authentication authentication,
                      HttpServletResponse response) throws ServletException, IOException;

    String tryLogin(LoginDto loginDto);
}
