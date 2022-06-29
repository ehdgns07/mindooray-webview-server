package com.nhnacademy.springboot.gatewayserver.adaptor.memberapi;

import com.nhnacademy.springboot.gatewayserver.domain.MemberRequest;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.Member;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.MemberDetailsDto;
import com.nhnacademy.springboot.gatewayserver.domain.memberlogin.TokenInformation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
@RequiredArgsConstructor
public class MemberAdaptorImpl implements MemberAdaptor {
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    @Override
    public MemberDetailsDto findUserDetails(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<MemberDetailsDto> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<MemberDetailsDto> member =
            restTemplate.exchange("http://localhost:8081/member/" + username,
                HttpMethod.GET,
                httpEntity,
                MemberDetailsDto.class);

        return member.getBody();
    }

    @Override
    public MemberDetailsDto insertMember(MemberRequest memberRequest) {
        ResponseEntity<MemberDetailsDto> member =
            restTemplate.postForEntity("http://localhost:8081/member",
                memberRequest,
                MemberDetailsDto.class);

        return member.getBody();
    }

    @Override
    public String loginAtGithub(StringBuilder sb) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                                                          .scheme("https")
                                                          .host("github.com")
                                                          .path("/login/oauth/authorize")
                                                          .queryParam("client_id",
                                                              "e3552959b3a52b993c48")
                                                          .queryParam("scope", "user:email,user")
                                                          .queryParam("state", sb.toString())
                                                          .build();
        return uriComponents.toString();
    }

    @Override
    public TokenInformation findToken(String code, String state) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                                                .scheme("https")
                                                .host("github.com")
                                                .path("/login/oauth/access_token")
                                                .queryParam("client_id", "e3552959b3a52b993c48")
                                                .queryParam("client_secret",
                                                    "760619bd131104fd1155380037600ed5b3671a40")
                                                .queryParam("code", code)
                                                .queryParam("redirect_url",
                                                    "localhost:8080/")
                                                .build();

        return restTemplate.getForObject(uri.toUri(), TokenInformation.class);
    }

    @Override
    public MemberDetailsDto findEmail(TokenInformation token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        UriComponents uri;
        httpHeaders.set("Authorization", token.getToken_type()+ " " + token.getAccess_token());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

//TODO username 받아오기

        uri = UriComponentsBuilder.newInstance()
                                  .scheme("https")
                                  .host("api.github.com")
                                  .path("user")
                                  .queryParam("username", "ehdgns07")
                                  .build();

        // ResponseEntity<Member> memberDto =
        ResponseEntity<MemberDetailsDto> memberDto =
            restTemplate.exchange(uri.toUri(),
                HttpMethod.GET,
                httpEntity,
                MemberDetailsDto.class);

        // MemberDetailsDto memberDetailsDto = modelMapper.map(memberDto, MemberDetailsDto.class);

        return memberDto.getBody();
        // return memberDetailsDto;
    }

    @Override
    public List<Member> findAll() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        HttpEntity<Member> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<Member>> member = restTemplate.exchange("http://localhost:8081/login",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<List<Member>>() {
            });
        return member.getBody();
    }
}
