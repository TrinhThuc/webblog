package com.example.webblog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.webblog.converter.CategoryConverter;
import com.example.webblog.dto.CategoryDTO;
import com.example.webblog.entity.CategoryEntity;
import com.example.webblog.respository.CategoryRepository;
import com.example.webblog.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public Map<String, String> findAll() {
        Map<String, String> result = new HashMap<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for (CategoryEntity item : entities) {
            result.put(item.getCode(), item.getName());
        }
        return result;
    }

    @Override
    public CategoryDTO findByCode(String code) {
        return categoryConverter.toDto(categoryRepository.findByCode(code));
    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO dto) {
        CategoryEntity category = new CategoryEntity();
        if (dto.getId() != null) {
            CategoryEntity oleCategory = categoryRepository.getById(dto.getId());
            category = categoryConverter.toEntity(dto, oleCategory);
        } else
            category = categoryConverter.toEntity(dto);
        return categoryConverter.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id : ids) {
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        for(CategoryEntity category : categoryEntities){
            CategoryDTO categoryDTO = categoryConverter.toDto(category);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

}
