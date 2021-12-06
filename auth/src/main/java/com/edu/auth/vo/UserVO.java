package com.edu.auth.vo;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class UserVO implements Serializable
{	private static final long serialVersionUID = 1L;	
	
	private String userName;
	private String password;
}
