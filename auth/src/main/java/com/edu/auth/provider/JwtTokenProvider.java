package com.edu.auth.provider;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProvider 
{	/*
	security:
	  jwt:
	    token:
	      secret-key: chave_microservice
	      expire-length: 360000
 	*/
	//segurança do nosso token
	@Value("${security.jwt.token.secret-key}")
	private String secretKey;
	
	@Value("${security.jwt.token.expire-length}")
	private long expireLength;
	
	@Qualifier("userService")
	@Autowired
	private UserDetailsService userDetailsService;
	
	/*Método para criptografar a senha*/
	@PostConstruct 
	protected void init()
	{	this.secretKey = Base64.getEncoder().encodeToString(
			this.secretKey.getBytes());
	}
	
	/*Método de criar o token*/
	public String createToken(String userName, List<String> roles)
	{	Claims claims = Jwts.claims().setSubject(userName);
		claims.put("roles", roles);
		//Ajustando a data de expiração
		Date now = new Date();
		Date expire = new Date(now.getTime() + expireLength);
		return Jwts.builder().setClaims(claims)
							.setIssuedAt(now)
							.setExpiration(expire)
							//Assinatura do algoritmo de criptografia
							.signWith(SignatureAlgorithm.HS256, this.secretKey)
							.compact();
	}
	
	public Authentication getAuthentication(String token)
	{	UserDetails userDetails = this.userDetailsService.loadUserByUsername(this.getUserName(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
	private String getUserName(String token)
	{	return Jwts.parser().setSigningKey(this.secretKey)
			.parseClaimsJws(token).getBody().getSubject();
	}
	
	/*Método para resolver o token*/
	public String resolveToken(HttpServletRequest request)
	{	String bearerToken = request.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Basic "))
		/*o numero 7 é a posição após a palavra bearer*/
		{return bearerToken.substring(6, bearerToken.length());}
		
		return null;
	}
	
	/*Método de validação do token*/
	public boolean validateToken(String token)
	{	try
		{	Jws<Claims> claims = Jwts.parser().setSigningKey(
				this.secretKey).parseClaimsJws(token);
			
			if(claims.getBody().getExpiration().before(new Date()))
				return false;
			
			return true;
		}
		catch(JwtException | IllegalArgumentException e) 
		{return false;}
	}
}