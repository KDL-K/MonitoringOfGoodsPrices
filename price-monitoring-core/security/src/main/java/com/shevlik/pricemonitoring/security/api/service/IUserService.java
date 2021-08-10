package com.shevlik.pricemonitoring.security.api.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.shevlik.pricemonitoring.security.model.dto.AuthorizedUserDto;
import com.shevlik.pricemonitoring.security.model.dto.EditedUserDto;
import com.shevlik.pricemonitoring.security.model.dto.LoginDto;
import com.shevlik.pricemonitoring.security.model.dto.UserDto;

public interface IUserService extends UserDetailsService{
	void signUp (UserDto user);
	UserDto getByLogin (String login);
	UserDto getById(Long id);
	AuthorizedUserDto signIn(LoginDto loginDto);
	void update(EditedUserDto dto);
	void changeUserPassword(LoginDto dto);
}
