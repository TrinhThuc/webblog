package com.example.webblog.respository;

import com.example.webblog.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findRoleEntityById(Long Id);
    RoleEntity findRoleEntityByCode(String code);
}
