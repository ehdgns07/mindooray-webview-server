package com.nhnacademy.springboot.gatewayserver.adaptor.memberapi;

import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.MemberDetailsDto;
import com.nhnacademy.springboot.gatewayserver.domain.MemberRequest;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.Member;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.TokenInformation;
import java.util.List;


public interface MemberAdaptor {
    MemberDetailsDto findUserDetails(String username);

    MemberDetailsDto insertMember(MemberRequest memberRequest);

    String loginAtGithub(StringBuilder sb);

    TokenInformation findToken(String code, String state);

    MemberDetailsDto findEmail(TokenInformation token);

    List<Member> findAll();
}
