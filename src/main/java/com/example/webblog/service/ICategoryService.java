package com.example.webblog.service;

import com.example.webblog.dto.CategoryDTO;
import com.example.webblog.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
	Map<String, String> findAll();
	CategoryDTO findByCode(String code);
	CategoryDTO save(CategoryDTO dto);
	void delete(long[] ids);
	List<CategoryDTO> getAll();
}
