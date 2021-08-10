package com.shevlik.pricemonitoring.security.model.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	@NotBlank (message = "'login' cannot be blank")
	private String login;
	@NotBlank (message = "'password' cannot be blank")
	private String password;
	@Email (message = "Email should be valid")
	private String email;
}
