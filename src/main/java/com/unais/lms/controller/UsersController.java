package com.unais.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unais.lms.dto.AuthRequest;
import com.unais.lms.dto.UsersReponse;
import com.unais.lms.service.JwtService;

@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.username);
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}

	}
}
