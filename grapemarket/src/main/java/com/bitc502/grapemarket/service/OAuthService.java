package com.bitc502.grapemarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitc502.grapemarket.common.AuthProvider;
import com.bitc502.grapemarket.common.Role;
import com.bitc502.grapemarket.exception.BadRequestException;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.payload.SignUpRequest;
import com.bitc502.grapemarket.repository.UserRepository;

import io.sentry.Sentry;

@Service
public class OAuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User setUser(SignUpRequest signUpRequest) {

		User result = new User();
		try {
			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				throw new BadRequestException("Email address already in use.");
			}
			User user = new User();
			user.setName(signUpRequest.getName());
			user.setEmail(signUpRequest.getEmail());
			user.setPassword(signUpRequest.getPassword());
			user.setProvider(AuthProvider.local);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole(Role.valueOf("USER"));

			result = userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return result;
	}
}
