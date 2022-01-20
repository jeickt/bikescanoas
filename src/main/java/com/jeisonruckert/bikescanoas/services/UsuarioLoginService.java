package com.jeisonruckert.bikescanoas.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.jeisonruckert.bikescanoas.security.UserSS;

public class UsuarioLoginService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
