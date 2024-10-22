package com.bilgeadam.NotesApp.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Configuration
public class SecurityConfig
{
	@Bean
	public SecurityFilterChain filterChainProd(HttpSecurity httpSecurity, AuthenticationConfiguration authenticationConfiguration) throws Exception
	{
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers("/note").authenticated().anyRequest().permitAll());
		httpSecurity.csrf(custom -> custom.disable());
		httpSecurity.formLogin(custom -> custom.loginPage("/login").defaultSuccessUrl("/note").failureHandler(loginFailHandler()));
		httpSecurity.logout(custom -> custom.logoutSuccessUrl("/login"));
		return httpSecurity.build();
	}

	private AuthenticationFailureHandler loginFailHandler(){
		return new AuthenticationFailureHandler(){

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
				if (exception!=null){
					if (exception.getClass() == DisabledException.class){
						response.sendRedirect("/login?err=1");
					} else if (exception.getClass() == BadCredentialsException.class) {
						response.sendRedirect("/login?err=2");
					} else if (exception.getClass() == InternalAuthenticationServiceException.class) {
						response.sendRedirect("/login?err=3");
					}else {
						response.sendRedirect("/login?err=4");
					}
				}
			}
		};
	}

	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}