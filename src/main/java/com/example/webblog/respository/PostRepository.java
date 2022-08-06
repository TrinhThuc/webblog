package com.example.webblog.respository;

import com.example.webblog.entity.CategoryEntity;
import com.example.webblog.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PostRepository extends JpaRepository<PostEntity, Long>{
    PostEntity findPostEntitiesById(Long id);
    List<PostEntity> findAllByCreatedBy(String name);
    Page<PostEntity> findAllByCategory_Code(String name, Pageable pageable);
    List<PostEntity> findAllByCategory_Code(String name);

    @Query(value = "select p from PostEntity p where p.title like %:tab% order by p.createdDate desc ")
    Page<PostEntity> findAllByTab(@Param("tab") String tab, Pageable pageable);

    @Query(value = "select p from PostEntity p where p.title like %:tab% order by p.createdDate desc ")
    List<PostEntity> findAllByTab(@Param("tab") String tab);

}
