package com.jeisonruckert.bikescanoas.resources;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping()
	public String get() {
		return "API do sistema de bicicletas de Canoas.";
	}
	
	@GetMapping("/userInfo")
	public UserDetails userInfo(@AuthenticationPrincipal UserDetails user) {
		return user;
	}
}

