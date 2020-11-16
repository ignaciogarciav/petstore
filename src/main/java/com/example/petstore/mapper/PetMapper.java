package com.example.petstore.mapper;

import com.example.petstore.entity.PetEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.generated.model.Pet;
import com.example.generated.model.Pet.StatusEnum;

@Component
public class PetMapper {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private TagMapper tagMapper;

	public PetEntity mapToEntity(Pet pet) {
		PetEntity petEntity = new PetEntity();
		petEntity.setCategory(categoryMapper.mapToEntity(pet.getCategory()));
		petEntity.setName(pet.getName());
		petEntity.setPhotoUrls(pet.getPhotoUrls());
		petEntity.setTags(tagMapper.mapListToEntity(pet.getTags()));
		petEntity.setStatus(pet.getStatus().toString());
		return petEntity;
	}

	public void updateEntity(PetEntity petEntity, Pet pet) {
		petEntity.setCategory(categoryMapper.mapToEntity(pet.getCategory()));
		petEntity.setName(pet.getName());
		petEntity.setPhotoUrls(pet.getPhotoUrls());
		petEntity.setTags(tagMapper.mapListToEntity(pet.getTags()));
		petEntity.setStatus(pet.getStatus().toString());
	}

	public Pet mapToDTO(PetEntity petEntity) {
		Pet pet = new Pet();
		pet.setId(petEntity.getId());
		pet.setCategory(categoryMapper.mapToDTO(petEntity.getCategory()));
		pet.setName(petEntity.getName());
		pet.setPhotoUrls(petEntity.getPhotoUrls());
		pet.setTags(tagMapper.mapListToDTO(petEntity.getTags()));
		pet.setStatus(StatusEnum.fromValue(petEntity.getStatus()));
		return pet;
	}
}
