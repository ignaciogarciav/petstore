package com.example.petstore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.generated.model.Pet;
import com.example.generated.model.ResponseApi;

@Service
public interface PetService {

	ResponseApi addNewPet(Pet pet);
	
	ResponseApi updatePet(Long id, Pet pet);
	
	List<Pet> findPetsByStatus(List<String> statues);
	
	Pet findPetById(Long id);
	
	ResponseApi updatePetWithFormData(Long id, String name, String status);
	
	ResponseApi uploadFile(Long id, MultipartFile file);
	
	ResponseApi deletePet(Long id);
}
