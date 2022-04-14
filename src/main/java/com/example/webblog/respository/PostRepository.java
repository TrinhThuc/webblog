package com.example.webblog.respository;

import com.example.webblog.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<PostEntity, Long>{
	

}
