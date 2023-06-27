package com.skilldistillery.pawrentsplace.services;

import com.skilldistillery.pawrentsplace.entities.User;

public interface AuthService {
	
	public User register(User user);
	User update(int userId, User user);
	public User getUserByUsername(String username);

}
