package com.edu.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edu.auth.entity.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AuthApplication 
{	public static void main(String[] args) 
	{SpringApplication.run(AuthApplication.class, args);}
	/*
	@Bean
	CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository,
			BCryptPasswordEncoder passwordEncoder)
	{return args -> {this.initUsers(userRepository, permissionRepository, passwordEncoder);};}
	
	private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository,
			BCryptPasswordEncoder passwordEncoder)
	{	Permission permission = null;
		Permission findPermission = permissionRepository.findByDescription("Admin");
		
		if(findPermission == null)
		{	permission = new Permission();
			permission.setDescription("Admin");
			permission = permissionRepository.save(permission);
		}
		
		else {permission = findPermission;}
		
		User admin = User.builder().userName("ewmu")
									.accountNonExpired(true)
									.accountNonLocked(true)
									.credentialsNonExpired(true)
									.enabled(true)
									.password(passwordEncoder.encode("123456"))
									.permissions(Arrays.asList(permission)).build();
		
		User find = userRepository.findByUserName(admin.getUsername());
		if(find == null) {userRepository.save(admin);}
	}*/
}