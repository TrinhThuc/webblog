package com.example.webblog.api.admin;

import com.example.webblog.dto.CategoryDTO;
import com.example.webblog.dto.PostDTO;
import com.example.webblog.service.ICategoryService;
import com.example.webblog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryAPI {
    @Autowired
    private ICategoryService categoryService;

    @PostMapping("api/category")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.save(categoryDTO);
    }

    @PutMapping("api/category/{id}")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable("id")long id){
        categoryDTO.setId(id);
        return categoryService.save(categoryDTO);
    }

    @DeleteMapping("api/category")
    public void deleteCategory(@RequestBody long[] ids) {
        categoryService.delete(ids);
    }
}
