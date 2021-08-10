package com.shevlik.pricemonitoring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shevlik.pricemonitoring.security.configuration.filter.JwtFilter;
import com.shevlik.pricemonitoring.security.configuration.filter.TokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;
	private final JwtAccessDeniedHandler accessDeniendHandler;
	
	
	@Bean
	public JwtFilter jwtFilter() {
		return new JwtFilter(tokenProvider);
	}

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
		        .exceptionHandling()
		        .authenticationEntryPoint(authenticationEntryPoint)
		        .accessDeniedHandler(accessDeniendHandler)
		        .and()
		        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		        .and()
		        .authorizeRequests()
		        .antMatchers("/signin").permitAll()
		        .antMatchers("/signup").permitAll()
		        .antMatchers("/histories/**").hasRole("USER")
		        .antMatchers("/users/**").hasRole("USER")
		        .antMatchers("/addresses/mod/**").hasRole("ADMIN")
		        .antMatchers("/assortment/mod/**").hasRole("ADMIN")
		        .antMatchers("/categories/mod/**").hasRole("ADMIN")
		        .antMatchers("/sessions/mod/**").hasRole("ADMIN")
		        .antMatchers("/products/mod/**").hasRole("ADMIN")
		        .antMatchers("/data-processing/**").hasRole("ADMIN")
		        .anyRequest().permitAll();
		http.addFilterBefore(jwtFilter(),UsernamePasswordAuthenticationFilter.class);
	}

}
