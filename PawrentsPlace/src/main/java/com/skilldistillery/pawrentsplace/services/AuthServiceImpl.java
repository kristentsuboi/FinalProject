package com.skilldistillery.pawrentsplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.User;
import com.skilldistillery.pawrentsplace.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User register(User user) {
		String encryptedPassword = encoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		
		user.setEnabled(true);
		
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User update(int userId, User user) {
		user.setEnabled(true);
		User managedUser = userRepo.findById(userId);
		if (managedUser != null) {
			managedUser.setRole(user.getRole());
			managedUser.setEmail(user.getEmail());
			managedUser.setPhone(user.getPhone());
			managedUser.setImageUrl(user.getImageUrl());
			return userRepo.saveAndFlush(managedUser);
		}
		return null;
	}
	
	@Override
	public boolean disable(int userId) {
		User managedUser = userRepo.findById(userId);
		if (managedUser != null) {
			managedUser.setEnabled(false);
			userRepo.saveAndFlush(managedUser);
			return true;
		}
		return false;
	}
	
	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
