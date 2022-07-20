	package com.example.webblog.respository;

import com.example.webblog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findOneByUserNameAndStatusAndEnabled(String name, int status, boolean enabled);
	UserEntity findByVerificationCode(String code);
	UserEntity findByEmailAndStatus(String email, int status);
}
