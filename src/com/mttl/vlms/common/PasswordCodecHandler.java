package com.mttl.vlms.common;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * PasswordCodecHandler
 * 
 * 
 *
 */
@Component("PasswordCodecHandler")
public class PasswordCodecHandler {
	private PasswordEncoder encoder;

	public PasswordCodecHandler() {
		encoder = new StandardPasswordEncoder("template_toyota_myanmar");
	}

	public String encode(String password) {
		return encoder.encode(password);
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encoder.matches(rawPassword, encodedPassword);
	}
}
