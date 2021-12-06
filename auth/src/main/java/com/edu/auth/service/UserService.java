package com.edu.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.auth.entity.User;
import com.edu.auth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService
{	private final UserRepository repository;

	@Autowired
	public UserService(UserRepository repository) {this.repository = repository;}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{	var user = this.repository.findByUserName(username);
		if(user != null)	{return user;}
		else {throw new UsernameNotFoundException("Username " + username + " not found");}
	}
}