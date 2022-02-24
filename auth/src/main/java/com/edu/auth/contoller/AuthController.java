package com.edu.auth.contoller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.auth.provider.JwtTokenProvider;
import com.edu.auth.repository.UserRepository;
import com.edu.auth.vo.UserVO;


//import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/login")
public class AuthController 
{	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtProvider;
	private final UserRepository userRepository;
	
	@Autowired
	public AuthController(AuthenticationManager authenticationManager, 
			JwtTokenProvider jwtProvider, UserRepository userRepository) 
	{	this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
		this.userRepository = userRepository;
	}
	
	/*Teste para validarmos nossa aplicação
	@RequestMapping("/testeSecurity")
	public String teste() {return "testado";}*/
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/token",
				produces = {"application/json", 
							"application/xml", 
							"application/x-yaml"},
				consumes = {"application/json", 
							"application/xml", 
							"application/x-yaml"})
	public ResponseEntity login(@RequestBody UserVO userVO)
	{	try
		{	var username = userVO.getUserName();
			var password = userVO.getPassword(); 
			
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password));
			
			var user = this.userRepository.findByUserName(username);
			var token = "";
			
			if(user != null)
			{token = this.jwtProvider.createToken(username, user.getRoles());}
			
			else {throw new UsernameNotFoundException("User name not found");}
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			
			return ResponseEntity.ok(model);
		}
		catch(AuthenticationException ae)
		{throw new BadCredentialsException("Invalid username/password");}
	}
}
