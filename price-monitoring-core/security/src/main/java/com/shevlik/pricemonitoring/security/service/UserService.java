package com.shevlik.pricemonitoring.security.service;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shevlik.pricemonitoring.security.api.dao.IRoleDao;
import com.shevlik.pricemonitoring.security.api.dao.IUserDao;
import com.shevlik.pricemonitoring.security.api.service.IUserService;
import com.shevlik.pricemonitoring.security.api.service.UserNameOccupiedException;
import com.shevlik.pricemonitoring.security.configuration.filter.TokenProvider;
import com.shevlik.pricemonitoring.security.model.Role;
import com.shevlik.pricemonitoring.security.model.RoleValue;
import com.shevlik.pricemonitoring.security.model.User;
import com.shevlik.pricemonitoring.security.model.dto.AuthorizedUserDto;
import com.shevlik.pricemonitoring.security.model.dto.EditedUserDto;
import com.shevlik.pricemonitoring.security.model.dto.LoginDto;
import com.shevlik.pricemonitoring.security.model.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Transactional
@Service
@RequiredArgsConstructor
@Log4j2
public class UserService implements IUserService{
	
	private final IUserDao userDao;
	private final IRoleDao roleDao;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	private final AuthenticationManager authenticationManager;
	private final ModelMapper mapper;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("UserService. IN loadUserByUsername - username: {}", username);
		User user = userDao.getByLogin(username);
		if (user == null) {
			log.warn("UserService. IN loadUserByUsername. No any user found with username: {}", username);
			throw new UsernameNotFoundException("No any user found with username " + username);
		}

		boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        return new org.springframework.security.core.userdetails.User(
                user.getLogin().toLowerCase(), user.getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, getAuthorities(user.getRoles()));
	}
	
	private static Set<GrantedAuthority> getAuthorities (Set<Role> roles) {
		log.info("UserService. IN private getAuthorities");
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getValue().name()));
        }
        return authorities;
    }

	@Override
	public void signUp (UserDto userDto) {
		log.info("UserService. IN signUp - try to register user: {}", userDto.getLogin());
		User user = mapper.map(userDto, User.class);
		log.info("UserService. IN signUp -  user: {}", user.getLogin());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleDao.findByEnumValue(RoleValue.ROLE_USER));
		user.setRoles(roles);
		
		log.info("UserService. IN signUp -  saving user: {}", user.getLogin());
		User userFromDB = userDao.getByLogin(user.getLogin());
		
		if (userFromDB != null) {
			log.warn("UserService. IN signUp - user with login: {} already exists", userDto.getLogin());
			throw new UserNameOccupiedException (userDto.getLogin() + " Login already exists");
		}
		
		userDao.save(user);
	
		log.info("UserService. IN signUp - user: {}, id: {} successfully registered", user.getLogin(), user.getId());	
	}
	
	@Override
	public AuthorizedUserDto signIn(LoginDto loginDto) {
		log.info("UserService. IN signIn");
		final UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getPassword());
		final Authentication authentication = authenticationManager.authenticate(authenticationToken);
		User user = userDao.getByLogin(loginDto.getLogin());
		final AuthorizedUserDto authorizedUser = new AuthorizedUserDto();
		authorizedUser.setLogin(user.getLogin());
		authorizedUser.setToken(tokenProvider.createToken(authentication));
		return authorizedUser;
	}

	@Override
	public UserDto getByLogin(String login) {
		log.info("UserService. IN getByLogin - try to found user by username: {}", login);
		
		User user = userDao.getByLogin(login);
		UserDto userDto = mapper.map(user, UserDto.class);
		
		log.info("UserService. IN getByLogin - user: {} found by username: {}", user, login);
		
		return userDto;
	}

	@Override
	public UserDto getById(Long id) {
		log.info("UserService. IN getById - try to found user by id: {}", id);
		
		User user = userDao.getById(id);
		UserDto userDto = mapper.map(user, UserDto.class);
		
		log.info("UserService. IN getById - user:{} found by id: {}", user, id);
		
		return userDto;
	}

	@Override
	public void update(EditedUserDto dto) {
		log.info("UserService. IN update");
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userDao.getByLogin(login);
		mapper.map(dto, user);
		userDao.update(user);
	}

	@Override
	public void changeUserPassword(LoginDto dto) {
		log.info("UserService. IN changeUserPassword");
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userDao.getByLogin(login);
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		userDao.update(user);
	}

}
