package com.nhnacademy.springboot.gatewayserver.domain;

import lombok.Data;

@Data
public class MemberRequest {
    private String username;
    private String password;
    private String email;
}
