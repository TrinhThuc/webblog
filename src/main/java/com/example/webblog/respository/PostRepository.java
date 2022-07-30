package com.example.webblog.respository;

import com.example.webblog.entity.CategoryEntity;
import com.example.webblog.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PostRepository extends JpaRepository<PostEntity, Long>{
    PostEntity findPostEntitiesById(Long id);

}
