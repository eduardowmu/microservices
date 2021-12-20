package com.edu.auth.configure;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.edu.auth.provider.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{	private final JwtTokenProvider jwtTokenProvider;

	@Autowired
	public SecurityConfig(JwtTokenProvider jwtTokenProvider) 
	{this.jwtTokenProvider = jwtTokenProvider;}
	 
	/*Metodo responsável pela parte de Criptografia*/
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception
	{return super.authenticationManagerBean();}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{	http.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests()
				  /*permitindo acesso sem necessidade de token*/
				  .antMatchers("/login").permitAll()
				  /*as demais, necessita de authentication*/
				  .anyRequest().authenticated()
			.and().apply(new JwtConfigurer(this.jwtTokenProvider));
	}
}
