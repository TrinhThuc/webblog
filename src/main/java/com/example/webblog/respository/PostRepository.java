package com.example.webblog.respository;

import com.example.webblog.entity.CategoryEntity;
import com.example.webblog.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<PostEntity, Long>{
    PostEntity findPostEntitiesById(Long id);
    List<PostEntity> findAllByCreatedBy(String name);
    Page<PostEntity> findAllByCategory_Name(String name, Pageable pageable);
    List<PostEntity> findAllByCategory_Name(String name);


}
