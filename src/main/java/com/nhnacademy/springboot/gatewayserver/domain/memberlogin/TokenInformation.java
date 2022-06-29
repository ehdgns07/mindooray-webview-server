package com.nhnacademy.springboot.gatewayserver.domain.memberlogin;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInformation {

	private String access_token;
	private String scope;
	private String token_type;
}
