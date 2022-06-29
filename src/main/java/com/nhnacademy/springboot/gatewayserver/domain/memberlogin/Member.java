package com.nhnacademy.springboot.gatewayserver.domain.memberlogin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
    private Long memberId;

    private String username;

    private String password;

    private String email;

    private String authority;

    private String status;
}
