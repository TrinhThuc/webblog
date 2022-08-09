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
    List<PostEntity> findAllByCreatedByOrderByCreatedDate(String name);

    @Query(value = "select p from PostEntity p where p.category.code = ?1 and p.isActive = true ")
    Page<PostEntity> findAllByCategory_CodeAndActiveIsTrue(String name , Pageable pageable);

    @Query(value = "select p from PostEntity p where p.category.code = ?1 and p.isActive = true ")
    List<PostEntity> findAllByCategory_CodeAndActiveIsTrue(String name);

    @Query(value = "select p from  PostEntity p where p.isActive = true ")
    Page<PostEntity> findAllByActiveIsTrue( Pageable pageable);

    @Query(value = "select p from  PostEntity p where p.isActive = true ")
    List<PostEntity> findAllByActiveIsTrue();

    @Query(value = "select p from PostEntity p where p.title like %:tab% and  p.isActive = :isActive order by p.createdDate desc ")
    Page<PostEntity> findAllByTab(@Param("tab") String tab,@Param("isActive") boolean isActive, Pageable pageable);

    @Query(value = "select p from PostEntity p where p.title like %:tab% and  p.isActive = :isActive order by p.createdDate desc ")
    List<PostEntity> findAllByTab(@Param("tab") String tab, @Param("isActive") boolean isActive);

}
