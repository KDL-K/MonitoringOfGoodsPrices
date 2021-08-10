package com.shevlik.pricemonitoring.security.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizedUserDto {
	private String login;
	private String token;
}
