package com.shevlik.pricemonitoring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shevlik.pricemonitoring.controller.util.ModelValidator;
import com.shevlik.pricemonitoring.security.api.service.IUserService;
import com.shevlik.pricemonitoring.security.model.dto.AuthorizedUserDto;
import com.shevlik.pricemonitoring.security.model.dto.LoginDto;
import com.shevlik.pricemonitoring.security.model.dto.UserDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SecurityController {
	
	private final IUserService userService;
	private final ModelValidator validator;
	
	@PostMapping ("/signin")
	public ResponseEntity<AuthorizedUserDto> signIn (@RequestBody LoginDto dto){
		validator.validate(dto);
		AuthorizedUserDto authorizedUser = userService.signIn(dto);
		return ResponseEntity.ok(authorizedUser);
	}
	
	
	@PostMapping ("/signup")
	public ResponseEntity<Void> signUp (@RequestBody UserDto dto){
		validator.validate(dto);
		userService.signUp(dto);
		return ResponseEntity.ok(null);
	}

}
