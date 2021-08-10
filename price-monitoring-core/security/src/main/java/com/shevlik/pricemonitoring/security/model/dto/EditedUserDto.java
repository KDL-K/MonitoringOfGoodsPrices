package com.shevlik.pricemonitoring.security.model.dto;

import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditedUserDto {
	@Email (message = "Email should be valid")
	private String email;
}
