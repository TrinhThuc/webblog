	package com.example.webblog.respository;

import com.example.webblog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


	public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findOneByUserNameAndStatusAndEnabled(String name, int status, boolean enabled);
	UserEntity findByVerificationCode(String code);
	UserEntity findByEmailAndStatus(String email, int status);

	@Query("SELECT u from UserEntity u where u.id = ?1")
	UserEntity findOneById(Long id);
}
