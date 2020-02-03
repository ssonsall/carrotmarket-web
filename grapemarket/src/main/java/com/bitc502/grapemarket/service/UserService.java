package com.bitc502.grapemarket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository uRepo;

	public User getUserById(int id) {
		User user = new User();
		try {
			Optional<User> oUser = uRepo.findById(id);
			user = oUser.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}


}
