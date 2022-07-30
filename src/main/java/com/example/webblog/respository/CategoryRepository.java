package com.example.webblog.respository;

import com.example.webblog.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
    CategoryEntity findCategoryEntitiesById(Long id);
}
