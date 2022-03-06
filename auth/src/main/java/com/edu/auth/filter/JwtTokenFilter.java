package com.edu.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.edu.auth.provider.JwtTokenProvider;

public class JwtTokenFilter extends GenericFilterBean 
{	private final JwtTokenProvider jwtToken;

	@Autowired
	public JwtTokenFilter(JwtTokenProvider jwtToken) 
	{this.jwtToken = jwtToken;}

	@Override
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{	String token = this.jwtToken.resolveToken(
			(HttpServletRequest)request);
	
		if(token != null && jwtToken.validateToken(token))
		{	Authentication auth = jwtToken.getAuthentication(token);
			if(auth != null)
			{SecurityContextHolder.getContext().setAuthentication(auth);}
			chain.doFilter(request, response);
		}
	}
}