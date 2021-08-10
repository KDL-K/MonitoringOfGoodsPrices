package com.shevlik.pricemonitoring.security.configuration.filter;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.shevlik.pricemonitoring.security.model.Role;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class TokenProvider implements InitializingBean{

	private static final String AUTHORITIES_KEY = "auth";
	public static final String DEFAULT_TOKEN_TYPE = "Bearer ";
	@Value("${security.jwt.token.secret}")
	private String jwtSecret;
	@Value("${security.jwt.token.validity}")
	private String tokenValidity;
	
	private Key key;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		
	}
	
	public String createToken(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		Date validity = new Date(new Date().getTime() + Long.parseLong(tokenValidity));
		return DEFAULT_TOKEN_TYPE + Jwts.builder()
				.setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
				.signWith(key, SignatureAlgorithm.HS512)
				.setExpiration(validity)
				.compact();
	}
	
	public String createToken (String username, Set<Role> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put (AUTHORITIES_KEY, getRoleNames(roles));
		
		Date validity = new Date(new Date().getTime() + Long.parseLong(tokenValidity));
		
		return DEFAULT_TOKEN_TYPE + Jwts.builder()
				.setClaims(claims)
				.setExpiration(validity)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
		
		
	private List<String> getRoleNames(Set<Role> roles) {
		List<String> result = new ArrayList<String>();
		roles.forEach(role -> {result.add(role.getValue().name());});
		return result;
	}

	public boolean validateToken (String authToken) {
		try {
			Jwts.parserBuilder()
			        .setSigningKey(key)
			        .build()
			        .parseClaimsJws(authToken);
			return true;
		}catch (SecurityException | MalformedJwtException e) {
			log.info("Invalid JWT signature.");
		}catch (ExpiredJwtException e) {
			log.info("Expired JWT token.");
		}catch (UnsupportedJwtException e) {
			log.info("Unsupported JWT token.");
		}catch (IllegalArgumentException e) {
			log.info("JWT token compact of handler are invalid.");
		}
		return false;
	}
	
	public Authentication getAuthentication (String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		
		User principal = new User(claims.getSubject(), "", authorities);
		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

	public String getUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}
	
}
