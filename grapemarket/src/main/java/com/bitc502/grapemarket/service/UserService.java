package com.bitc502.grapemarket.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bitc502.grapemarket.common.AuthProvider;
import com.bitc502.grapemarket.common.Role;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.UserRepository;

import io.sentry.Sentry;

@Service
public class UserService {

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Value("${file.path}")
	private String fileRealPath;

	public User getUserById(int id) {
		User user = new User();
		try {
			Optional<User> oUser = uRepo.findById(id);
			user = oUser.get();
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return user;
	}

	public int usernameCheck(String username) {
		try {
			if (uRepo.existsByUsername(username)) {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return -1;
	}

	public int join(User user, MultipartFile userProfile) {

		try {
			String rawPassword = user.getPassword();
			String encPassword = passwordEncoder.encode(rawPassword);
			user.setPassword(encPassword);
			user.setProvider(AuthProvider.local);
			user.setRole(Role.valueOf("USER"));
			if (userProfile.getSize() != 0) {
				String UUIDFileName = UUID.randomUUID() + "_" + userProfile.getOriginalFilename();
				user.setUserProfile(UUIDFileName);
				Path filePath = Paths.get(fileRealPath + UUIDFileName);
				Files.write(filePath, userProfile.getBytes());
			}
			uRepo.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
			return -1;
		}

	}

	public int update(User user, String currentUserProfile, MultipartFile userProfile) {
		try {
			String rawPassword = user.getPassword();
			String encPassword = passwordEncoder.encode(rawPassword);
			Optional<User> oUser = uRepo.findById(user.getId());
			user = oUser.get();
			user.setPassword(encPassword);
			if (userProfile.getSize() != 0) {
				String UUIDFileName = UUID.randomUUID() + "_" + userProfile.getOriginalFilename();
				user.setUserProfile(UUIDFileName);
				Path filePath = Paths.get(fileRealPath + UUIDFileName);
				Files.write(filePath, userProfile.getBytes());
			} else {
				user.setUserProfile(currentUserProfile);
			}
			uRepo.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
			return -1;
		}

	}

	public int addUpdate(User user) {
		try {
			uRepo.addUpdate(user.getAddress(), user.getAddressX(), user.getAddressY(), user.getId());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
			return -1;
		}
	}

	public int authUpdate(User user) {
		try {
			System.out.println("인증 업데이트");
			uRepo.authUpdate(user.getId());
			return user.getId();
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
			return -1;
		}
	}

	public int delete(int id) {
		try {
			uRepo.deleteById(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
			return -1;
		}
	}

}
