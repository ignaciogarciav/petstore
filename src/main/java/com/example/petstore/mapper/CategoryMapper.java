package com.example.petstore.mapper;

import org.springframework.stereotype.Component;

import com.example.generated.model.Category;
import com.example.petstore.entity.CategoryEntity;

@Component
public class CategoryMapper {

	public CategoryEntity mapToEntity(Category category) {
		
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setName(category.getName());
		return categoryEntity;
	}


	public Category mapToDTO(CategoryEntity categoryEntity) {
		Category category = new Category();
		category.setId(categoryEntity.getId());
		category.setName(categoryEntity.getName());
		return category;
	}
}
