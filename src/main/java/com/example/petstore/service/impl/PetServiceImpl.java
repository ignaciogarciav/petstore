package com.example.petstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.generated.model.Pet;
import com.example.generated.model.ResponseApi;
import com.example.petstore.entity.PetEntity;
import com.example.petstore.exceptions.ResourceNotFoundException;
import com.example.petstore.mapper.PetMapper;
import com.example.petstore.repository.PetRepository;
import com.example.petstore.service.PetService;
import com.example.petstore.utils.ResponseApiFactory;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	private PetRepository petRepository;
	@Autowired
	private ResponseApiFactory responseFactory;
	@Autowired
	private PetMapper petMapper;

	@Override
	public ResponseApi addNewPet(Pet pet) {
		PetEntity petEntity = petMapper.mapToEntity(pet);
		petRepository.save(petEntity);
		return responseFactory.createNew201Response();
	}

	@Override
	public ResponseApi updatePet(Long id, Pet pet) {

		Optional<PetEntity> petEntityOptional = petRepository.findById(id);
		if (petEntityOptional.isPresent()) {
			PetEntity petEntity = petEntityOptional.get();
			petMapper.updateEntity(petEntity, pet);
			petRepository.save(petEntity);
			return responseFactory.createNew200Response();
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public List<Pet> findPetsByStatus(List<String> statues) {
		List<PetEntity> petEntityList = petRepository.findAll();
		List<Pet> petList = new ArrayList<Pet>();
		for (PetEntity petEntity : petEntityList) {
			for (String status : statues) {
				if (status.equals(petEntity.getStatus())) {
					petList.add(petMapper.mapToDTO(petEntity));
				}
			}
		}
		if (petList.isEmpty()) {
			throw new ResourceNotFoundException();
		} else {
			return petList;
		}

	}

	@Override
	public Pet findPetById(Long id) {
		Optional<PetEntity> petEntityOptional = petRepository.findById(id);
		if (petEntityOptional.isPresent()) {
			Pet pet = petMapper.mapToDTO(petEntityOptional.get());
			return pet;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public ResponseApi updatePetWithFormData(Long id, String name, String status) {
		Optional<PetEntity> petEntityOptional = petRepository.findById(id);

		if (petEntityOptional.isPresent()) {
			PetEntity petEntity = petEntityOptional.get();
			petEntity.setId(petEntity.getId());
			petEntity.setCategory(petEntity.getCategory());
			petEntity.setName(name);
			petEntity.setPhotoUrls(petEntity.getPhotoUrls());
			petEntity.setTags(petEntity.getTags());
			petEntity.setStatus(status);
			petRepository.save(petEntity);
			return responseFactory.createNew200Response();
		} else {

			throw new ResourceNotFoundException();
		}
	}

	@Override
	public ResponseApi deletePet(Long id) {
		Optional<PetEntity> petEntityOptional = petRepository.findById(id);
		if (petEntityOptional.isPresent()) {
			PetEntity petEntity = petEntityOptional.get();
			petRepository.delete(petEntity);
			return responseFactory.createNew200Response();
		} else {

			throw new ResourceNotFoundException();
		}
	}

}
