package com.example.petstore.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.generated.api.PetApi;
import com.example.generated.model.Pet;
import com.example.generated.model.ResponseApi;
import com.example.petstore.service.PetService;

@RestController
public class PetController implements PetApi{
	
	@Autowired
	private PetService petService;

	@Override
	public ResponseEntity<ResponseApi> addPet(@Valid Pet body) {
		return new ResponseEntity<ResponseApi>(petService.addNewPet(body), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseApi> deletePet(Long petId, String apiKey) {
		return ResponseEntity.ok(petService.deletePet(petId));
	}

	@Override
	public ResponseEntity<List<Pet>> findPetsByStatus(@NotNull List<String> status) {
		return ResponseEntity.ok(petService.findPetsByStatus(status));
	}

	@Override
	public ResponseEntity<List<Pet>> findPetsByTags(@NotNull List<String> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Pet> getPetById(Long petId) {
		return ResponseEntity.ok(petService.findPetById(petId));
	}

	@Override
	public ResponseEntity<ResponseApi> updatePet(Long petId, @Valid Pet body) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(petService.updatePet(petId, body));
	}

	@Override
	public ResponseEntity<ResponseApi> updatePetWithForm(Long petId, String name, String status) {
		return ResponseEntity.ok(petService.updatePetWithFormData(petId, name, status));
	}

	@Override
	public ResponseEntity<ResponseApi> uploadFile(Long petId, String additionalMetadata, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

}
