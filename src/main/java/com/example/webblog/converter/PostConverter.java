package com.example.webblog.converter;

import com.example.webblog.dto.PostDTO;
import com.example.webblog.entity.PostEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class PostConverter {

	@Autowired
	ModelMapper modelMapper;

	public PostDTO toDto(PostEntity entity) {
		PostDTO result = modelMapper.map(entity, PostDTO.class);
		return result;
	}
	
	public PostEntity toEntity(PostDTO dto) {
		PostEntity result = modelMapper.map(dto, PostEntity.class);
		return result;
	}
	
	public PostEntity toEntity(PostEntity result, PostDTO dto) {
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setContent(dto.getContent());
		result.setThumbnail(dto.getThumbnail());
		return result;
	}
}