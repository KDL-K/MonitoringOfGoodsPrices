package com.shevlik.pricemonitoring.security.configuration.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import lombok.RequiredArgsConstructor;

import static com.shevlik.pricemonitoring.security.configuration.filter.TokenProvider.DEFAULT_TOKEN_TYPE;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean{
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	private final TokenProvider tokenProvider;
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = resolveToken(httpServletRequest);
		if(tokenProvider.validateToken(token)) {
			Authentication authentication = tokenProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
		
	}

	private String resolveToken(HttpServletRequest httpServletRequest) {
		String bearerToken = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(DEFAULT_TOKEN_TYPE)) {
			return bearerToken.substring(DEFAULT_TOKEN_TYPE.length(), bearerToken.length());
		}
		return null;
	}

}