	package com.example.webblog.respository;

import com.example.webblog.entity.CommentEntity;
import com.example.webblog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
    }
