package com.example.webblog.service;

import com.example.webblog.dto.CategoryDTO;
import com.example.webblog.entity.CategoryEntity;

import java.util.Map;

public interface ICategoryService {
	Map<String, String> findAll();
	CategoryDTO save(CategoryDTO dto);
	void delete(long[] ids);
}
