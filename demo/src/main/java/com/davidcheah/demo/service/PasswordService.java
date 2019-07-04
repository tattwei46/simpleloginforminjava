package com.davidcheah.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public String encodes(String password) {
		return passwordEncoder.encode(password);
	}
	
	public boolean matches(String password, String hashedPassword) {
		return passwordEncoder.matches(password, hashedPassword);
	}
}
