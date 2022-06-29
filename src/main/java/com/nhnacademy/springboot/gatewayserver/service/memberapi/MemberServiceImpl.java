package com.nhnacademy.springboot.gatewayserver.service.memberapi;

import com.nhnacademy.springboot.gatewayserver.adaptor.memberapi.MemberAdaptor;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.MemberDetailsDto;
import com.nhnacademy.springboot.gatewayserver.domain.MemberRequest;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.LoginDto;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.Member;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.TokenInformation;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberAdaptor memberAdaptor;
    private final RedisTemplate<String, String> redisTemplate;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationSuccessHandler loginSuccessHandler;

    @Override
    public MemberDetailsDto register(MemberRequest memberRequest) {
        memberRequest.setPassword(passwordEncoder.encode(memberRequest.getPassword()));
        return memberAdaptor.insertMember(memberRequest);
    }

    @Override
    public String tryToLoginAtGithub() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        StringBuilder sb = new StringBuilder();
        Long randomLong;

        for (int i = 0; i < 10; i++) {
            randomLong = random.nextLong();
            sb.append(randomLong);
        }

        return memberAdaptor.loginAtGithub(sb);
    }

    @Override
    public TokenInformation getToken(String code, String state) {

        return memberAdaptor.findToken(code, state);
    }

    @Override
    public MemberDetailsDto requestEmail(TokenInformation token) {

        return memberAdaptor.findEmail(token);
    }

    @Override
    public String checkEmail(MemberDetailsDto memberDetailsDto, HttpServletRequest request,
                             Authentication authentication,
                             HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberAdaptor.findAll();

        for(Member member : members){
            if(Objects.equals(member.getEmail(), memberDetailsDto.getEmail())){
                memberDetailsDto.setAuthority(member.getAuthority());

                loginSuccessHandler.onAuthenticationSuccess(request, response, authentication);

                return "";
             }
        }
        return "/login";
    }

    @Override
    public String tryLogin(LoginDto loginDto) {

        MemberDetailsDto memberDetailsDto = memberAdaptor.findUserDetails(loginDto.getUsername());
        if(passwordEncoder.matches(loginDto.getPassword(), memberDetailsDto.getPassword())){
            return "projects/index";
        }

        return "/login";
    }


}
