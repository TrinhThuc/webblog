package com.example.webblog.converter;

import com.example.webblog.dto.CategoryDTO;
import com.example.webblog.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CategoryConverter {
	@Autowired
	ModelMapper modelMapper;

	public CategoryDTO toDto(CategoryEntity entity) {
		CategoryDTO result = modelMapper.map(entity, CategoryDTO.class);
		return result;
	}
	
	public CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity result = modelMapper.map(dto, CategoryEntity.class);
		return result;
	}

	public CategoryEntity toEntity(CategoryDTO dto, CategoryEntity entity){
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		return entity;
	}
}
