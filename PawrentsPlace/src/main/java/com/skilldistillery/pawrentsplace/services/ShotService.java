package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import com.skilldistillery.pawrentsplace.entities.Shot;

public interface ShotService {
	List<Shot> index(String username, int petId);
	Shot show(String username, int shotId);
	Shot create(String username, int petId, Shot shot);
	Shot update(String username, int shotId, Shot shot, int petId);
	boolean delete(String username, int shotId, int petId);

}
