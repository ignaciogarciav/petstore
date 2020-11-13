package com.example.petstore.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.generated.model.Tag;
import com.example.petstore.entity.TagEntity;

@Component
public class TagMapper {
	
	public TagEntity mapToEntity(Tag tag) {
		TagEntity tagEntity = new TagEntity();
		tagEntity.setName(tag.getName());
		return tagEntity;
	}

	public List<TagEntity> mapListToEntity(List<Tag> tagList){
		List<TagEntity> tagEntityList = new ArrayList<TagEntity>();
		for(Tag tag : tagList) {
			tagEntityList.add(mapToEntity(tag));
		}
		return tagEntityList;
	}
	
	public Tag mapToDTO(TagEntity tagEntity) {
		Tag tag = new Tag();
		tag.setId(tagEntity.getId());
		tag.setName(tagEntity.getName());
		return tag;
	}
	public List<Tag> mapListToDTO(List<TagEntity> tagEntityList){
		List<Tag> tagList = new ArrayList<Tag>();
		for(TagEntity tagEntity : tagEntityList) {
			tagList.add(mapToDTO(tagEntity));
		}
		return tagList;
	}
}
