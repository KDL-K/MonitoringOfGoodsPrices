package com.shevlik.pricemonitoring.security.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	@NotBlank (message = "'login' cannot be blank")
	private String login;
	@NotBlank (message = "'password' cannot be blank")
	private String password;
}
