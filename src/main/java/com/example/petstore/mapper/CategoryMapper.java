package com.example.petstore.mapper;

import org.springframework.stereotype.Component;

import com.example.generated.model.Category;
import com.example.petstore.entity.CategoryEntity;

@Component
public class CategoryMapper {

	public CategoryEntity mapToEntity(Category category) {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setId(category.getId());
		categoryEntity.setName(category.getName());
		return categoryEntity;
	}

	public CategoryEntity updateEntity(CategoryEntity categoryEntity, Category category) {
		if(categoryEntity.getName().trim().equalsIgnoreCase(category.getName())) {
			return categoryEntity;
		}else {
			CategoryEntity categoryEntity2 = new CategoryEntity();
			categoryEntity2.setName(category.getName());
			return categoryEntity2;
		}
	}

	public Category mapToDTO(CategoryEntity categoryEntity) {
		Category category = new Category();
		category.setId(categoryEntity.getId());
		category.setName(categoryEntity.getName());
		return category;
	}
}
