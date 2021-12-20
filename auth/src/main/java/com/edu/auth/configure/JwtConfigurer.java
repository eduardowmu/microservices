package com.edu.auth.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edu.auth.filter.JwtTokenFilter;
import com.edu.auth.provider.JwtTokenProvider;

public class JwtConfigurer extends 
	SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> 
{	private final JwtTokenProvider jwtTokenProvider;

	@Autowired
	public JwtConfigurer(JwtTokenProvider jwtTokenProvider) 
	{this.jwtTokenProvider = jwtTokenProvider;}
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception
	{	JwtTokenFilter filter = new JwtTokenFilter(this.jwtTokenProvider);
		
		httpSecurity.addFilterBefore(filter, 
				UsernamePasswordAuthenticationFilter.class);
	}
}
