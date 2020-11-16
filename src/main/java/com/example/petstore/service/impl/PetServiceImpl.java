package com.example.petstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.generated.model.Pet;
import com.example.generated.model.ResponseApi;
import com.example.generated.model.Pet.StatusEnum;
import com.example.petstore.entity.CategoryEntity;
import com.example.petstore.entity.PetEntity;
import com.example.petstore.exceptions.ResourceNotFoundException;
import com.example.petstore.mapper.PetMapper;
import com.example.petstore.repository.CategoryRepository;
import com.example.petstore.repository.PetRepository;
import com.example.petstore.service.PetService;
import com.example.petstore.utils.ResponseApiFactory;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	private PetRepository petRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ResponseApiFactory responseFactory;
	@Autowired
	private PetMapper petMapper;

	@Override
	public ResponseApi addNewPet(Pet pet) {
		try {
			PetEntity petEntity = petMapper.mapToEntity(pet);
			Optional<CategoryEntity> categoryOptional = categoryRepository.findByName(pet.getCategory().getName());
			if (categoryOptional.isPresent()) {
				petEntity.setCategory(categoryOptional.get());
			}
			petRepository.save(petEntity);
			return responseFactory.createNew201Response();
		} catch (NullPointerException e) {
			throw new NullPointerException("Invalid status, category or tags");
		}
	}

	@Override
	public ResponseApi updatePet(Long id, Pet pet) {

		try {
			Optional<PetEntity> petEntityOptional = petRepository.findById(id);
			if (petEntityOptional.isPresent()) {
				PetEntity petEntity = petEntityOptional.get();
				petMapper.updateEntity(petEntity, pet);
				Optional<CategoryEntity> categoryOptional = categoryRepository.findByName(pet.getCategory().getName());
				if (categoryOptional.isPresent()) {
					petEntity.setCategory(categoryOptional.get());
				}
				petRepository.save(petEntity);
				return responseFactory.createNew200Response();
			} else {
				throw new ResourceNotFoundException();
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("Invalid status, category or tags");
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
			Pet pet = petMapper.mapToDTO(petEntityOptional.get());
			PetEntity petEntity = petEntityOptional.get();
			if (name != null) {
				pet.setName(name);
				petEntity.setName(pet.getName());
			}
			try {
				if (status != null) {
					pet.setStatus(StatusEnum.fromValue(status));
					petEntity.setStatus(pet.getStatus().toString());
				}
			} catch (NullPointerException ex) {
				throw new NullPointerException("Invalid status");
			}
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

	@Override
	public ResponseApi uploadFile(Long id, MultipartFile file) {

		Optional<PetEntity> petEntityOptional = petRepository.findById(id);
		if (petEntityOptional.isPresent()) {
			PetEntity petEntity = petEntityOptional.get();
			petEntity.getPhotoUrls().add(file.getOriginalFilename());
			petRepository.save(petEntity);
			return responseFactory.createNew200Response();
		} else {
			throw new ResourceNotFoundException();
		}
	}

}
