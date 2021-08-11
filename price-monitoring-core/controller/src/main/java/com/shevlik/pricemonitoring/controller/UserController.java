package com.shevlik.pricemonitoring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shevlik.pricemonitoring.controller.util.ModelValidator;
import com.shevlik.pricemonitoring.security.api.service.IUserService;
import com.shevlik.pricemonitoring.security.model.dto.EditedUserDto;
import com.shevlik.pricemonitoring.security.model.dto.LoginDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/users")
@RequiredArgsConstructor
public class UserController {
	
	private final IUserService userService;
	private final ModelValidator validator;
	
	@PutMapping ("/editing")
	public ResponseEntity<Void> editUser (@RequestBody EditedUserDto dto){
		validator.validate(dto);
		userService.update(dto);
		return ResponseEntity.ok(null);	
	}
	
	@PutMapping ("/editing/new-password")
	public ResponseEntity<Void> changeUserPassword (@RequestBody LoginDto dto){
		validator.validate(dto);
		userService.changeUserPassword(dto);
		return ResponseEntity.ok(null);	
	}
	
	

}
