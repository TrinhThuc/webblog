package com.example.webblog.converter;

import com.example.webblog.dto.UserDTO;
import com.example.webblog.entity.RoleEntity;
import com.example.webblog.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {
    @Autowired
    ModelMapper modelMapper;

    public UserDTO toDto(UserEntity entity) {
        List<RoleEntity> roleEntityList = entity.getRoles();
        List<Long> roles = new ArrayList<>();
        for (RoleEntity role : roleEntityList) {
            roles.add(role.getId());
        }
        UserDTO result = new UserDTO();
        result = modelMapper.map(entity, UserDTO.class);
        result.setRoleIds(roles);
        return result;
    }

    public UserEntity toEntity(UserDTO dto) {
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }

    public UserEntity toEntity(UserEntity entity, UserDTO dto) {
        if (dto.getUserName() != null && dto.getUserName() != "")
            entity.setUserName(dto.getUserName());
        if (dto.getPassword() != null && dto.getPassword() != "")
            entity.setPassword(dto.getPassword());
        if (dto.getFullname() != null && dto.getFullname() != "")
            entity.setFullname(dto.getFullname());
        if (dto.getEmail() != null && dto.getEmail() != "")
            entity.setEmail(dto.getEmail());
        if (dto.getAvatar() != null && dto.getAvatar() != "")
            entity.setAvatar(dto.getAvatar());

        return entity;
    }

}
