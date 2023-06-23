package com.skilldistillery.pawrentsplace.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.Pet;
import com.skilldistillery.pawrentsplace.entities.Shot;
import com.skilldistillery.pawrentsplace.repositories.PetRepository;
import com.skilldistillery.pawrentsplace.repositories.ShotRepository;

@Service
public class ShotServiceImpl implements ShotService {
	@Autowired
	private ShotRepository shotRepo;
	@Autowired
	private PetRepository petRepo;

	@Override
	public List<Shot> index(String username, int petId) {
		Pet pet = petRepo.findById(petId);
		if (pet != null) {
			return shotRepo.findByPet_Id(petId);

		}
		return null;
	}

	@Override
	public Shot show(String username, int shotId) {
		return shotRepo.findById(shotId);
	}

	@Override
	public Shot create(String username, int petId, Shot shot) {
		Pet pet = petRepo.findByUser_UsernameAndId(username, petId);
		if (pet != null) {
			shot.setPet(pet);
			Shot managedShot = shotRepo.saveAndFlush(shot);
			return managedShot;
		}

		return null;
	}

	@Override
	public Shot update(String username, int shotId, Shot shot, int petId) {
		Pet managedPet = petRepo.findByUser_UsernameAndId(username, petId);
		Shot managedShot = shotRepo.findById(shotId);
		if (managedShot != null) {
			managedShot.setName(shot.getName());
			managedShot.setDateAdministered(shot.getDateAdministered());
			managedShot.setFrequency(shot.getFrequency());
			managedShot.setNotes(shot.getNotes());
			return shotRepo.saveAndFlush(managedShot);
		}
		return null;
	}

	@Override
	public boolean delete(String username, int shotId, int petId) {
		Pet pet = petRepo.findById(petId);
		Shot deleted = shotRepo.findById(shotId);
		if (deleted != null) {
			ArrayList<Shot> shots = new ArrayList<>(pet.getShots());
			for (Shot shot : shots) {
				if (shot.getId() == shotId) {
					pet.getShots().remove(shot);
				}
			}
			petRepo.saveAndFlush(pet);
			shotRepo.delete(deleted);
			return true;
		} else {
			return false;
		}
	}

}
